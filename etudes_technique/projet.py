# -*- coding: utf-8 -*-
'''
created: Mon Jan 11 21:06:14 2021
last modified: Mon Jan 11 21:06:14 2021
@author: David ING
'''
'''
import os.path, time
print("created: %s" % time.ctime(os.path.getctime(file)))
print("last modified: %s" % time.ctime(os.path.getmtime(file)))
'''
#Library
import requests
import re
import json
import xmltodict
import pandas as pd
from lxml import etree
from lxml.etree import XMLSyntaxError
from redis import Redis
from tqdm import tqdm
from six import text_type
#for entities-fishing module for client
from nerd import nerd_client
#join url
from urllib.parse import urljoin
#for disable logger debug
import logging.config

NS = {'tei': 'http://www.tei-c.org/ns/1.0'}

def pdf_convert_xml(pdf_link):
    '''
    function that convert pdf file to xml string
    ''' 
    GROBID_URL = 'http://localhost:8080'
    url = '%s/api/processFulltextDocument' % GROBID_URL
    pdf = requests.get(pdf_link)
    xml = requests.post(url, files={'input': pdf.content})
    return xml.text



def tei_to_dict(tei):
    parser = etree.XMLParser(encoding='UTF-8', recover=True)
    tei = tei if not isinstance(tei, text_type) else tei.encode('utf-8')
    root = etree.fromstring(tei, parser)
    #dictionary
    result = {}

    #Partie abstract
    abstract = get_abstract(root)
    if abstract and len(abstract) == 1:
        result['abstract'] = abstract[0].text
    else:
        result['abstract'] = ""

    #Partie auteurs
    authors = get_authors(root)
    if authors:
        result['authors'] = list(map(element_to_author, authors))
    else:
        result['authors'] = ""

    #Partie mots clés
    keywords = get_keywords(root)
    if keywords and len(keywords) == 1:
        result['keywords'] = extract_keywords(keywords[0])
    else:
        result['keywords'] = ""

    #Partie titre    
    title = get_title(root)
    if title and len(title) == 1:
        result['title'] = title[0].text
    else:
        result['title'] = ""

    #Partie body    
    body = get_body(root)    
    if body:
        result['body'] = " ".join(body)
    else:
        result['body'] = ""
    
    #Partie references
    references = get_references(root)
    if references:
        result['references'] = list(map(element_to_reference, references))
    else:
        result['references'] = ""

    return result


def element_to_author(el):
    result = {}

    name = []

    first = el.xpath('.//tei:persName/tei:forename[@type="first"]', namespaces=NS)
    if first and len(first) == 1:
        name.append(first[0].text)

    middle = el.xpath('.//tei:persName/tei:forename[@type="middle"]', namespaces=NS)
    if middle and len(middle) == 1:
        name.append(middle[0].text + '.')

    surname = el.xpath('.//tei:persName/tei:surname', namespaces=NS)
    if surname and len(surname) == 1:
        name.append(surname[0].text)

    result['name'] = ' '.join(name)

    affiliations = []
    for aff in el.xpath('.//tei:affiliation', namespaces=NS):
        for institution in aff.xpath('.//tei:orgName[@type="institution"]',
                                     namespaces=NS):
            affiliations.append({
                'value': institution.text
            })

    result['affiliations'] = affiliations

    return result


def extract_keywords(el):
    return [{'value': e.text} for e in el.xpath('.//tei:term', namespaces=NS)]


def element_to_reference(el):
    result = {}

    result['ref_title'] = extract_reference_title(el)

    result['authors'] = [
        element_to_author(e) for e in el.xpath('.//tei:author', namespaces=NS)
    ]

    result['journal_pubnote'] = extract_reference_pubnote(el)

    return result


def extract_reference_title(el):
    title = el.xpath(
        './/tei:analytic/tei:title[@level="a" and @type="main"]',
        namespaces=NS
    )
    if title and len(title) == 1:
        return title[0].text


def extract_reference_pubnote(el):
    result = {}

    journal_title = el.xpath('./tei:monogr/tei:title', namespaces=NS)
    if journal_title and len(journal_title) == 1:
        result['journal_title'] = journal_title[0].text

    journal_volume = el.xpath(
        './tei:monogr/tei:imprint/tei:biblScope[@unit="volume"]',
        namespaces=NS
    )
    if journal_volume and len(journal_volume) == 1:
        result['journal_volume'] = journal_volume[0].text

    journal_issue = el.xpath(
        './tei:monogr/tei:imprint/tei:biblScope[@unit="issue"]',
        namespaces=NS
    )
    if journal_issue and len(journal_issue) == 1:
        result['journal_issue'] = journal_issue[0].text

    year = el.xpath(
        './tei:monogr/tei:imprint/tei:date[@type="published"]/@when',
        namespaces=NS
    )
    if year and len(year) == 1:
        result['year'] = year[0]

    pages = []

    page_from = el.xpath(
        './tei:monogr/tei:imprint/tei:biblScope[@unit="page"]/@from',
        namespaces=NS
    )
    if page_from and len(page_from) == 1:
        pages.append(page_from[0])

    page_to = el.xpath(
        './tei:monogr/tei:imprint/tei:biblScope[@unit="page"]/@to',
        namespaces=NS
    )
    if page_to and len(page_to) == 1:
        pages.append(page_to[0])

    result['page_range'] = '-'.join(pages)

    return result

def get_abstract(root):
    return root.xpath('//tei:profileDesc/tei:abstract/tei:p', namespaces=NS)

def get_authors(root):
    return root.xpath('//tei:fileDesc//tei:author', namespaces=NS)

def get_keywords(root):
    return root.xpath('//tei:profileDesc/tei:textClass/tei:keywords', namespaces=NS)

def get_references(root):
    return root.xpath('//tei:text//tei:listBibl/tei:biblStruct', namespaces=NS)

def get_title(root):
    return root.xpath('//tei:titleStmt/tei:title', namespaces=NS)

def get_body(root):
    return root.xpath("//tei:body//text()", namespaces=NS)    


def descripteurs(url_agritrop):
    '''
    function that extracts the description of agrovoc and geographical (i.e. the key word of agrovoc and geographical)
    '''
    descripteurs_agrovoc = []
    descripteurs_geo = []
    #find id number in the url
    num = re.findall(r'\d+', url_agritrop)
    id_num = str(num[0])
    host = "http://agritrop.cirad.fr/cgi/export/eprint/"
    pattern = "/Simple/agritrop-eprint-"
    extension = ".txt"
    rel = id_num + pattern + id_num + extension
    #reconstruct the new url link automatically through the Metadata file text
    url_metadata = urljoin(host, rel)
    #request to url of publication
    response = requests.get(url_metadata)
    data = response.text
    for d in data.split("\n"):
        #find the pattern that cotains the word: 'agrovoc_mat_motcle:'
        pattern1 = re.compile(r'agrovoc_mat_motcle:')
        #find the pattern that cotains the word: 'agrovoc_geo_motcle:'
        pattern2 = re.compile(r'agrovoc_geo_motcle:')

        if (pattern1.findall(d)):
            key_word_1 = d.replace('agrovoc_mat_motcle:','')
            descripteurs_agrovoc.append(key_word_1)
        
        if (pattern2.findall(d)):
            key_word_2 = d.replace('agrovoc_geo_motcle:','')
            descripteurs_geo.append(key_word_2)
            
    return descripteurs_agrovoc, descripteurs_geo
'''
descripteurs_agrovoc, descripteurs_geo = descripteurs("https://agritrop.cirad.fr/551172")
print(descripteurs_agrovoc)
print(descripteurs_geo) 
'''


def response_entity_fishing(text):
    '''
    function that can use entity-fishing online: https://github.com/hirmeos/entity-fishing-client-python
    '''
    #Disable the logger.debug message
    logging.config.dictConfig({
    'version': 1,
    'disable_existing_loggers': True,
    })
    client = nerd_client.NerdClient()
    response = client.disambiguate_text(text)
    return response[0]['entities']

def knowledge_base(wikidataId):
    '''
    function that generates the wikidata link from an input Id
    '''
    domaine_name = "https://www.wikidata.org/wiki/"

    return urljoin(domaine_name,wikidataId)

def entities_linking(dictionaire):
    '''
    function that extracts a text and generate some keywords with their correspond wikidata page (knowledge base)
    '''
    entities_dict ={}
    for item in dictionaire:
        try:
            entities_dict[item["rawName"]]= knowledge_base(item["wikidataId"])
        except:
            pass

    return entities_dict


def entities_agrovoc(dictionary):
    '''
    function that generate annotation concept id from the dictionary of entities-linking
    '''
    l = list()
    for key in dictionary:
        l.append(agrovoc.find_with_agrovoc(key))
    #remove empty set in case there are no concepts matching between entity_fishing and agrovoc    
    l2 = [x for x in l if x != set()]
    return l2


'''
xml = pdf_convert_xml('http://agritrop.cirad.fr/551172/1/document_551172.pdf')
dictionary = tei_to_dict(xml)
a = response_entity_fishing(dictionary.get('body'))
b = entities_linking(a)
print(b)
'''

def evaluate_grobid(file_csv):
    '''
    Function to evaluate the performance of the grobid method 
    '''
    file_read = pd.read_csv(file_csv)
    fail = 0
    for i, row in file_read.iterrows():
        pdf = row['ACCES_TEXTE_INTEGRAL']
        xml = pdf_convert_xml(pdf)
        try:
            extraction = tei_to_dict(xml)
            if extraction.get('title') != "" and extraction.get("abstract") != "" and extraction.get("body") != "":
                print(i, " Extraction successful for title, abstract, and body text.")
            else:
                print("At least one of the title or abstract or body text cannot be extracted.")    

        except Exception as e:
            print(i,' Failed to process: ',pdf)
            print("Error Type: ", e)
            fail += 1      
    print("Accuracy: ", (i + 1) - (fail/(i+1) )*100,"%")


#evaluate_grobid("corpus_titres_abstracts_corps_eng_articles-type_1_2_4_100_limit.csv")
#evaluate_grobid("corpus_titres_abstracts_corps_fre_articles-type_1_2_4_100_limit.csv")
