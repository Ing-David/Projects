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

namespaces = {
    'tei': 'http://www.tei-c.org/ns/1.0'
}

redis = Redis(decode_responses=True)


def pdf_convert_xml(pdf_link):
    '''
    function that convert pdf file to xml string
    ''' 
    GROBID_URL = 'http://localhost:8080'
    url = '%s/api/processFulltextDocument' % GROBID_URL
    pdf = requests.get(pdf_link)
    xml = requests.post(url, files={'input': pdf.content})
    return xml.text

def descripteurs(file_csv):
    file_name = pd.read_csv(file_csv)
    descripteurs_agrovoc = []
    descripteurs_geo = []
   
    file_name['GEO']=file_name['GEO'].fillna("") #fill column NaN with ""
    for i, row in file_name.iterrows():
        pdf = row['ACCES_TEXTE_INTEGRAL']
        description = row['DESCRIPTEURS']
        geo = row['GEO']
        description = list(map(lambda x: x.strip(), row["DESCRIPTEURS"].split(";")))
        geo = list(map(lambda x: x.strip(), row["GEO"].split(";")))
        des_agrovoc = list(set(description) - set(geo))
        descripteurs_agrovoc.append(des_agrovoc)
        descripteurs_geo.append(geo)

    return descripteurs_agrovoc, descripteurs_geo

#descripteurs_agrovoc, descripteurs_geo  = descripteurs("corpus_titres_abstracts_corps_eng_articles-type_1_2_4_100_limit.csv") 

def extract_structure_pdf(pdf_file):
    '''
    function that converts the pdf file to title text, abstruct text, and body text
    '''
    xml_text = pdf_convert_xml(pdf_file)
    # we need to get rid of the xml declaration
    pattern = re.compile(r'<\?xml.*\?>')  
    xml = pattern.sub('', xml_text)
    try:
        root = etree.fromstring(xml, base_url="http://www.tei-c.org/ns/1.0/")
        #title
        title = root.xpath("//tei:fileDesc/tei:titleStmt/tei:title/text()", namespaces=namespaces)

        #convert string of json to dict with json.loads
        ab = json.loads(json.dumps(xmltodict.parse(xml_text)))
        #abstract
        abstract = ab['TEI']['teiHeader']['profileDesc']['abstract']['p']

        #body
        body = root.xpath("//tei:body//text()", namespaces=namespaces)
        body = " ".join(body)

        if len(title) > 0:
            title = title[0]
        else:
            title = ""

        if len(abstract) > 0:
            abstract = abstract
        else:
            abstract = ""

        return title.strip(), abstract.strip(), body.strip()
    except XMLSyntaxError:
        return "", "", ""


#title, abstract, body = extract_structure_pdf("https://agritrop.cirad.fr/557447/1/document_557447.pdf")

