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
import operator
import math
from lxml import etree
from lxml.etree import XMLSyntaxError
from redis import Redis
from tqdm import tqdm
from six import text_type
from collections import Counter
#for entities-fishing module for client
from nerd import nerd_client
#join url
from urllib.parse import urljoin
#for disable logger debug
import logging.config
#for generate the statistic of precison, recall, and f1-score
from scipy import stats
import statistics
import operator


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

#################################################
# Helper functions
#################################################

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

def contains_word(s, w):
    '''
       boolean function to verify whether a word contain subword or not
    '''
    return f' {w} ' in f' {s} '

#################################################
# Main function
#################################################

def tei_to_dict(tei):
    '''
    function to extract the data from xml format
    '''
    #pattern of xml tag
    pattern = re.compile(r'<\?xml.*\?>')
    #replace all the matching xml tag by ''  
    xml = pattern.sub('', tei)
    root = etree.fromstring(xml, base_url="http://www.tei-c.org/ns/1.0/")
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


def descripteurs(url_agritrop):
    '''
    function that extracts the description of agrovoc and geographical 
    (i.e. the key word of agrovoc and geographical)
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

    descripteurs = descripteurs_agrovoc + descripteurs_geo

    return descripteurs

'''
descripteurs = descripteurs("https://agritrop.cirad.fr/551172")
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

def entities_linking(liste):
    '''
    function that extracts a text and generate some keywords with their correspond wikidata page (knowledge base)
    '''
    l = []
    for item in liste:
        try:
            couple = ( item["rawName"], knowledge_base(item["wikidataId"]) )
            l.append(couple)           
        except:
            pass
    return l

def count_annotation(list_tuple):
    '''
    function that count all the annotations from the output of entities linking function
    '''
    annotation_list =[]
    #put in list for all annotations
    for annotation in list_tuple:
        annotation_list.append(annotation[0])
    #merge annotations regardless of the capital letters
    orig = Counter(annotation_list)
    lower = Counter(map(str.lower, annotation_list))
    merge_list = {}
    for k_orig in orig:
        k_lower = k_orig.lower()
        if lower[k_lower] == orig[k_orig]:
            merge_list[k_orig] = orig[k_orig]
        else:
            merge_list[k_lower] = lower[k_lower]
    #sort the annotations depends on how many times they appear in descending order
    sorted_annotations = dict(sorted(merge_list.items(), key=operator.itemgetter(1),reverse=True) )

    return sorted_annotations

def sort_length_annotations(dictionary):
    '''
    function that sort the number of words of annotations in descending order
    '''
    new_dictionary ={}
    keys = sorted(dictionary.keys(), key=lambda k: len(k.split()),reverse=True)
    for k in keys:
        new_dictionary[k] = dictionary[k]

    return new_dictionary

def OldScoreNcbo(dictionary):
    '''
    function that generate Old Annotator scoring method
    '''
    new_dictionary = {}
    key_set = set(dictionary)
    for k,v in dictionary.items():
        #concept's preferred name
        new_dictionary[k]= 10*v

    return new_dictionary     

def Cvalue_score(dictionary):
    '''
    function to calculate the C-value score of annotations
    '''
    new_dictionary = {}
    found = 0
    sub_key = 0
    for k,v in dictionary.items():
        for k2,v2 in dictionary.items():
            if k != k2 and k in k2:
                found += 1
                #loop to delete all the nested terms
                for k3,v3 in dictionary.items():
                    if k2 != k and k2 != k3 and contains_word(k3,k2):
                        #first, delete all the nested frenquency terms         
                        sub_key += -v3
                #finally, add that nested terms frequency        
                sub_key += v2                                
        #annotations not nested        
        if found == 0:
            c_value =  float((math.log(len(k.split()),2))) * v 
            new_dictionary[k] = c_value  

        #nested annotations
        else: 
            c_value =  float((math.log(len(k.split()),2))) * (v - ( (1/found)*sub_key ) ) 
            new_dictionary[k] = c_value
            found = 0
            sub_key = 0

    return new_dictionary


def ScoreNcboCvalue(dictionary_Cvalue,dictionary_OldScoreNcbo):
    '''
    function that generates ScoreNcboCvalue method
    '''
    new_dictionary = {}
    c_value = 0
    for k,v in dictionary_Cvalue.items():
        for k1,v1 in dictionary_Cvalue.items():
            if k != k1 and k in k1:
                c_value += v1
        
        if c_value != 0:
            new_dictionary[k] = float (math.log(dictionary_OldScoreNcbo[k],10)) * c_value
            c_value = 0
        else:
            new_dictionary[k] = float (math.log(dictionary_OldScoreNcbo[k],10))

    sorted_score_dictionary = dict(sorted(new_dictionary.items(), key=operator.itemgetter(1),reverse=True))

    return sorted_score_dictionary


#################################################
# Evaluation function
#################################################

def evaluate_globid_title(file_csv):
    '''
    Function to evaluate the performance of the grobid method based on Title
    '''
    file_read = pd.read_csv(file_csv)
    success = 0
    for i, row in file_read.iterrows():
        pdf = row['ACCES_TEXTE_INTEGRAL']
        xml = pdf_convert_xml(pdf)
        try:
            extraction = tei_to_dict(xml)
            if (len(extraction.get("title"))!= 0):
                print(i, "Title text is successfully extracted.")
                success += 1
            else:
                print(i, " Fail to process title's text: ",pdf)

        except Exception as e:
            print(i,' Failed to process: ',pdf)
            print("Error Type: ", e)
     
    print("Accuracy's title text: ", (success/(i+1))*100,"%")

def evaluate_globid_abstract(file_csv):
    '''
    Function to evaluate the performance of the grobid method based on Abstract    
    '''
    file_read = pd.read_csv(file_csv)
    success = 0
    for i, row in file_read.iterrows():
        pdf = row['ACCES_TEXTE_INTEGRAL']
        xml = pdf_convert_xml(pdf)
        try:
            extraction = tei_to_dict(xml)
            if (len(extraction.get("abstract"))!= 0):
                print(i, "Abstract text is successfully extracted.")
                success += 1
            else:
                print(i, " Fail to process abstract's text: ",pdf)

        except Exception as e:
            print(i,' Failed to process: ',pdf)
            print("Error Type: ", e)
     
    print("Accuracy's abstract text: ", (success/(i+1))*100,"%")

def evaluate_grobid_body(file_csv):
    '''
    Function to evaluate the performance of the grobid method based on Body's text
    '''
    file_read = pd.read_csv(file_csv)
    success = 0
    for i, row in file_read.iterrows():
        pdf = row['ACCES_TEXTE_INTEGRAL']
        xml = pdf_convert_xml(pdf)
        try:
            extraction = tei_to_dict(xml)
            if (len(extraction.get("body"))!= 0):
                print(i, "Body text is successfully extracted.")
                success += 1
            else:
                print(i, " Fail to process body's text: ",pdf)

        except Exception as e:
            print(i,' Failed to process: ',pdf)
            print("Error Type: ", e)
     
    print("Accuracy's body text: ", (success/(i+1))*100,"%")

def evaluate_grobid_ensemble(file_csv):
    '''
    Function to evaluate the performance of the grobid method based on Title, Abstract, and Body text together
    '''
    file_read = pd.read_csv(file_csv)
    success = 0
    for i, row in file_read.iterrows():
        pdf = row['ACCES_TEXTE_INTEGRAL']
        xml = pdf_convert_xml(pdf)
        try:
            extraction = tei_to_dict(xml)
            if (len(extraction.get("title"))!= 0) and (len(extraction.get("abstract"))!= 0) and (len(extraction.get("body"))!= 0):
                print(i, "Title text, abstract text, and body text are successfully extracted.")
                success += 1
            else:
                print(i, " Fail to process title or abstract or body text: ",pdf)

        except Exception as e:
            print(i,' Failed to process: ',pdf)
            print("Error Type: ", e)
     
    print("Accuracy: ", (success/(i+1))*100,"%")


def evaluation_sans_filtre(file_csv,text_choice):
    '''
    Evaluation the concepts generate with description global
    arg text_choice: can be 'abstract' or 'body'
    '''
    dictionary={}
    file_read = pd.read_csv(file_csv)

    for line, column in file_read.iterrows():
        found = 0
        pdf = column['ACCES_TEXTE_INTEGRAL']
        xml = pdf_convert_xml(pdf)
        try:
            a = tei_to_dict(xml)
            b = response_entity_fishing(a.get(text_choice))
            c = entities_linking(b)
            d = count_annotation(c)
            e = list(d.keys())
            #list of annotations generated
            f = [x.lower() for x in e]           
            g = column['DESCRIPTEURS_ANGLAIS']
            #convert to list
            h = g.split(";")
            #remove space between each string
            i = [x.strip(' ') for x in h]
            #global descriptions
            j = [x.lower() for x in i]
            if len(j) > 0:
                #intersection between relevant documents and retrieved documents
                intersect = list(set(j).intersection(set(f)))
                found = len(intersect)
                evaluation = {}                  
                evaluation["precision"] = found/(len(e))
                evaluation["recall"] = found/(len(j))
                evaluation["f1_score"] = (2*evaluation["precision"]*evaluation["recall"])/(evaluation["precision"]+evaluation["recall"])
                dictionary["Publication"+str(line+1)] = evaluation
        
            else:
                print("No global descriptions to calculate the scores.")

        except Exception as error:
            print(error)

    return dictionary

#evaluation_sans_filtre('corpus_titres_abstracts_corps_eng_articles-type_1_2_4_100_limit.csv','body')

def statistic_evaluation(nested_dictionary):
    '''
    fonction to generate the statistic value for all values of precision, recall, 
    and f1-score from all the publications using nested dictionary from function **evaluation_sans_filtre**
    '''
    precision_list = []
    recall_list = []
    f1_score_list = []
    for k,v in dictionary.items():
        precision_list.append(v["precision"])
        recall_list.append(v["recall"])
        f1_score_list.append(v["f1_score"])

    #statistic analysis    
    precision_statistic = stats.describe(precision_list)
    recall_statistic = stats.describe(recall_list)
    f1_score_statistic = stats.describe(f1_score_list)

    return precision_statistic, recall_statistic, f1_score_statistic

def f1_score_top_k_concepts_Cvalue_score(file_csv,limit_line,text_choice,threshold):   
    '''
    function to calcute mean value of f1_score with k concepts assignment

    arg limit_line: limit line in csv file
    arg text_choice: can be abstract or body
    arg threshold: top k concepts 

    '''

    file_read = pd.read_csv(file_csv)
    f1_score_Cvalue_list = []
    for line, column in file_read.iterrows():
        if (line < limit_line):
            found = 0
            precision = 0
            recall = 0
            f1_score = 0
            pdf = column['ACCES_TEXTE_INTEGRAL']
            xml = pdf_convert_xml(pdf)
            try:
                a = tei_to_dict(xml)
                b = response_entity_fishing(a.get(text_choice))
                c = entities_linking(b)
                d = count_annotation(c)
                e = sort_length_annotations(d)
                f = Cvalue_score(e)
                #sorted CvalueScore
                g = dict(sorted(f.items(), key=operator.itemgetter(1),reverse=True))
                #select top k threshold concepts
                h = list(g.keys())[:threshold]
                i = [x.lower() for x in h]
                #part global descriptions
                j = column['DESCRIPTEURS_ANGLAIS']
                #convert to list
                k = j.split(";")
                #remove space between each string
                l = [x.strip(' ') for x in k]
                #global descriptions
                m = [x.lower() for x in l]
                if len(m) > 0:
                    intersect = list(set(m).intersection(set(i)))
                    found = len(intersect)
                    if (found > 0):
                        precision = found/(len(i))
                        recall = found/(len(m))
                        f1_score = (2*precision*recall)/(precision+recall)
                        f1_score_Cvalue_list.append(f1_score)
                    else:
                        f1_score_Cvalue_list.append(0)
                else:
                    print("No global descriptions to compare.")
            except Exception as error:
                print(error)

    mean_f1_Cvalue_score = statistics.mean(f1_score_Cvalue_list)

    return mean_f1_Cvalue_score

#f1_score_top_k_concepts_Cvalue_score('corpus_eng_articles-type_1_2_1000_limit.csv',400,'body',10)

def f1_score_top_k_concepts_NcboCvalue(file_csv,limit_line,text_choice,threshold):   
    '''
    function to calcute mean value of f1_score with k concepts assignment

    arg limit_line: limit line in csv file
    arg text_choice: can be abstract or body
    arg threshold: top k concepts 

    '''

    file_read = pd.read_csv(file_csv)
    f1_score_NcboCvalue_list = []

    for line, column in file_read.iterrows():
        if (line < limit_line):
            found = 0
            precision = 0
            recall = 0
            f1_score = 0
            pdf = column['ACCES_TEXTE_INTEGRAL']
            xml = pdf_convert_xml(pdf)
            try:
                a = tei_to_dict(xml)
                b = response_entity_fishing(a.get(text_choice))
                c = entities_linking(b)
                d = count_annotation(c)
                e = sort_length_annotations(d)
                f = OldScoreNcbo(e)
                g = Cvalue_score(e)
                h = ScoreNcboCvalue(g,f)               
                #select top k threshold concepts
                i = list(h.keys())[:threshold]
                j = [x.lower() for x in i]               
                #part global descriptions 
                k = column['DESCRIPTEURS_ANGLAIS']
                #convert to list
                l = k.split(";")
                #remove space between each string
                m = [x.strip(' ') for x in l]
                #global descriptions
                n = [x.lower() for x in m]
                if len(n) > 0:
                    intersect = list(set(n).intersection(set(j)))
                    found = len(intersect)
                    if (found > 0):
                        precision = found/(len(j))
                        recall = found/(len(n))
                        f1_score = (2*precision*recall)/(precision+recall)
                        f1_score_NcboCvalue_list.append(f1_score)
                    else:
                        f1_score_NcboCvalue_list.append(0)
                else:
                    print("No global descriptions to compare.")
            except Exception as error:
                print(error)

    mean_f1_NcboCvalue_score = statistics.mean(f1_score_NcboCvalue_list)

    return mean_f1_NcboCvalue_score

def compare_top_k_concept_Cvalue_score(file_csv,limit_line,text_choice,threshold_value):
    '''
    function to choose the best top k concepts with the Cvalue scoring method
    '''

    compare_dictionary = {}
    for i in threshold_value:
        compare_dictionary[i] = f1_score_top_k_concepts_Cvalue_score(file_csv,limit_line,text_choice,i)
    
    best_top_k_concept = max(compare_dictionary.iteritems(), key=operator.itemgetter(1))[0]
    
    return best_top_k_concept

def compare_top_k_concept_NcboCvalue(file_csv,limit_line,text_choice,threshold_value):
    '''
    function to choose the best top k concepts with the NcboCvalue scoring method
    '''    
    compare_dictionary = {}

    for i in threshold_value:
        compare_dictionary[i] = f1_score_top_k_concepts_NcboCvalue(file_csv,limit_line,text_choice,i)
    
    best_top_k_concept = max(compare_dictionary.iteritems(), key=operator.itemgetter(1))[0]
    
    return best_top_k_concept
