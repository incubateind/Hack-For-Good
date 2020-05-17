import requests
import json
import io
import random
import flask
from flask import Flask,render_template,request,Response,send_from_directory
from matplotlib.backends.backend_agg import FigureCanvasAgg as FigureCanvas
from matplotlib.figure import Figure
import time
from pandas.plotting import register_matplotlib_converters
from flask_caching import Cache
import matplotlib.pyplot as plt
register_matplotlib_converters()
#%matplotlib inline
import plotly.express as px
import plotly.graph_objects as go
from plotly.subplots import make_subplots
import plotly.io as pio

plt.rcParams['figure.figsize'] = [15, 5]
from IPython import display
from ipywidgets import interact, widgets

app = Flask(__name__)
# Check Configuring Flask-Caching section for more details
cache = Cache(app, config={'CACHE_TYPE': 'simple'})
import pickle

file = open('model.pkl', 'rb')
clf = pickle.load(file)
file.close()

import numpy as np 
import pandas as pd 
import plotly as py

from jinja2 import Template
from IPython.display import HTML
import json
import re

from plotly.offline import download_plotlyjs, init_notebook_mode, plot, iplot

df=pd.DataFrame()
df_countries=pd.DataFrame()
df_countrydate=pd.DataFrame()

@app.route('/')
@app.route('/home')
@cache.cached(timeout=50)
def home():
    url = "https://coronavirus-monitor.p.rapidapi.com/coronavirus/worldstat.php"
    headers = {
    'x-rapidapi-host': "coronavirus-monitor.p.rapidapi.com",
    'x-rapidapi-key': "d1f7e44bbamsh541e009b32d0df9p18e59cjsnc222439ff00f"
    }
    response = requests.get(url, headers=headers)
    params={
        'api_key':'0e230a7838364ca4baf2b954dc827c11',
    }
    r2 = requests.get(
      'http://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=0e230a7838364ca4baf2b954dc827c11',
      params=params)
    return render_template('index.html',articles=json.loads(r2.text)['articles'],world=json.loads(response.text))






        





@app.route('/googlef5f481dd6625c9da.html')
def analytics():
    return render_template('googlef5f481dd6625c9da.html')

    
    
    
    

@app.route('/robots.txt')
@app.route('/sitemap.xml')
def static_from_root():
    return send_from_directory(app.static_folder, request.path[1:])

#@app.route('/wmap.html',methods=['GET', 'POST'])


# def show_map():

#     global df
#     global df_countries
#     global df_countrydate
#     url = 'https://raw.githubusercontent.com/datasets/covid-19/master/data/time-series-19-covid-combined.csv'
#     df = pd.read_csv(url)
#     df = df.rename(columns={'Country/Region':'Country'})
#     df_countries = df.groupby(['Country', 'Date']).sum().reset_index().sort_values('Date', ascending=False)
#     df_countries = df_countries.drop_duplicates(subset = ['Country'])
#     df_countries = df_countries[df_countries['Confirmed']>0]
#     df_countrydate = df[df['Confirmed']>0]
#     df_countrydate = df_countrydate.groupby(['Date','Country']).sum().reset_index()
    
#     fig = go.Figure(data=go.Choropleth(
#         locations = df_countries['Country'],
#         locationmode = 'country names',
#         z = df_countries['Confirmed'],
#         colorscale = 'Reds',
#         marker_line_color = 'black',
#         marker_line_width = 0.5,
#     ))
#     fig.update_layout(
#         title_text = 'Confirmed Cases as of March 28, 2020',
#         title_x = 0.5,
#         geo=dict(
#             showframe = False,
#             showcoastlines = False,
#             projection_type = 'equirectangular'
#         )
#     )
#     Manipulating the original dataframe
    
#     Creating the visualization
#     fig = px.choropleth(df_countrydate, 
#                         locations="Country", 
#                         locationmode = "country names",
#                         color="Confirmed", 
#                         hover_name="Country", 
#                         animation_frame="Date"
#                     )
#     fig.update_layout(
#         title_text = 'Global Spread of Coronavirus',
#         title_x = 0.5,
#         geo=dict(
#             showframe = False,
#             showcoastlines = False,
#         ))
#     py.offline.plot(fig, filename='templates/wmap.html',auto_open=False)
#     return render_template('wmap.html') 


@app.route('/indiastat')
@cache.cached(timeout=50)
def indiastat():
    url = "https://api.rootnet.in/covid19-in/stats/latest"
    response = requests.get(url) 
    url = "https://api.rootnet.in/covid19-in/contacts"
    k = requests.get(url)
    url = "https://api.rootnet.in/covid19-in/stats/hospitals"
    r = requests.get(url)
    ur= "http://newsapi.org/v2/top-headlines?country=in&apiKey=0e230a7838364ca4baf2b954dc827c11"
    r2= requests.get(ur)
    urls = "https://coronavirus-monitor.p.rapidapi.com/coronavirus/cases_by_country.php"
    headers = {
    'x-rapidapi-host': "coronavirus-monitor.p.rapidapi.com",
    'x-rapidapi-key': "d1f7e44bbamsh541e009b32d0df9p18e59cjsnc222439ff00f"
    }
    r3 = requests.get(urls, headers=headers)
    world=json.loads(r3.text)['countries_stat']
    for i in world:
        if(i['country_name']=='India'):
            ca=i['cases']
            tr=i['total_recovered']
            de=i['deaths']
            break
    return render_template('examples/tables.html',ca=ca,tr=tr,de=de,regional=json.loads(response.text)['data']['regional'],rg=json.loads(k.text)['data']['contacts']['regional'], hs=json.loads(r.text)['data']['regional'],news=json.loads(r2.text)['articles'],overall=json.loads(response.text)['data']['summary'])



@app.route('/tracker')
@cache.cached(timeout=50)
def tracker():
    url = "https://coronavirus-monitor.p.rapidapi.com/coronavirus/worldstat.php"
    headers = {
    'x-rapidapi-host': "coronavirus-monitor.p.rapidapi.com",
    'x-rapidapi-key': "d1f7e44bbamsh541e009b32d0df9p18e59cjsnc222439ff00f"
    }                                                                                                                                                           
    r1 = requests.get(url, headers=headers)
    params={
        'api_key':'0e230a7838364ca4baf2b954dc827c11',
    }
    r2 = requests.get(
      'http://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=0e230a7838364ca4baf2b954dc827c11',
      params=params)
    urls = "https://coronavirus-monitor.p.rapidapi.com/coronavirus/cases_by_country.php"
    r3 = requests.get(urls, headers=headers)
    r4 = requests.get('http://newsapi.org/v2/top-headlines?sources=google-news&apiKey=0e230a7838364ca4baf2b954dc827c11',params=params)
    return render_template('examples/dashboard.html',articles=json.loads(r2.text)['articles'],world=json.loads(r1.text),countries_stat=json.loads(r3.text)['countries_stat'],wnews=json.loads(r4.text)['articles'])

# @app.route('/profile')
# def profile():
#     return render_template('examples/profile.html')



@app.route('/tst', methods=["GET", "POST"])
def tst():
    if request.method == "POST":
        myDict = request.form
        fever = float(myDict['fever'])
        age = int(myDict['age'])
        pain = int(myDict['pain'])
        runnyNose = int(myDict['dryCough'])
        diffBreath = int(myDict['diffBreath'])
        # Code for Inference
        inputFeatures = [fever, pain, age, runnyNose, diffBreath]
        infProb = clf.predict([inputFeatures])
        if infProb == 1:
            res="You may Have Positive Symptoms"
        else:
            res="You are Probably Safe"
        print(infProb)
        return render_template('examples/show.html', inf=res)
    return render_template('examples/profile.html')
    # return 'Hello, World!' + str(infProb)


@app.route('/indiastate', methods=['GET','POST'])
def indiastate():
    url = "https://api.rootnet.in/covid19-in/stats/latest"
    response = requests.get(url)
    regional=json.loads(response.text)['data']['regional']
    url = "https://api.rootnet.in/covid19-in/contacts"
    k = requests.get(url)
    url = "https://api.rootnet.in/covid19-in/stats/hospitals"
    r = requests.get(url)
    ur= "http://newsapi.org/v2/top-headlines?country=in&apiKey=0e230a7838364ca4baf2b954dc827c11"
    r2= requests.get(ur)
    res="Rajasthan-AshkokGehlot.jpg"
    res2="rajasthan.png"
    res3="Chief Minister Mr. Ashok Gehlot"
    for region in regional:
        if (region['loc'] == "Rajasthan"):
            a=region['confirmedCasesIndian']
            b=region['discharged']
            c=region['deaths']
            d=33
    res4=["https://www.mohfw.gov.in/pdf/GuidelinesfornotifyingCOVID-19affectedpersonsbyPrivateInstitutions.pdf",
    "https://www.mohfw.gov.in/pdf/DistrictWiseList324.pdf",
    "https://www.who.int/docs/default-source/wrindia/situation-report/india-situation-report-8.pdf?sfvrsn=cd671813_2",
    "https://education.rajasthan.gov.in/content/dam/doitassets/education/medicaleducation/sms-medical-college-jaipur/pdf/News/Govt.%20Instructions%20for%20COVID%2019.pdf",
    "https://cmrelief.rajasthan.gov.in/ContributionCovid-19.aspx"]
    res5=["During the lockdown period in the state, supply of essential commodities will be done from house to house, supply of ration items, dairy products, vegetables and medicines will be done.",
            "Important decisions of Chief Minister for relief from Corona crisis.Big relief to farmers, industries and general public Electricity and water bills will be postponed for two months.",
            "In view of the effect of Corona virus by the state government, it has been decided to provide whole grains in the form of pulses, wheat to about 35 lakh beneficiaries through Anganwadi centers. Under this, 35 lakh families will be given 1 kg of pulses and 2 to 3 kg of wheat in a month under Integrated Child Development Services Scheme. This system will remain in force for the duration of the corona virus.",
            "Immediate financial assistance of Rs.1000 / - deposited in the account of 15.78 lakh construction workers.",
            "Apart from this, 36 lakh 51 thousand BPL,Beneficiaries of State BPL and Antyodaya Yojana,25 lakh construction workers and registered street vendors who are not covered under Social Security Pension Scheme, will be given one thousand rupees as a fixed ex-gratia amount so that they will get cash in their hands and they can meet their daily needs. Will be able to do it.",
            "Shri Gehlot has instructed to give 2 months pension simultaneously to the beneficiaries of the state's 78 lakh social security pension.",
            "Due to the lock down by the state government, liquor license shops, hotel bars, restaurant bars have been given relief by deciding certain fee waivers and postponement.",
            "Chief Minister has given permission to extend the term of interest waiver scheme-2019 of Agricultural Marketing Department and extension of waiver of outstanding mandi duty on imported agricultural commodities and sugar for agricultural processing from outside the state till June 30.",
            "A monthly honorarium of Rs 5000 has been sanctioned to such senior journalists above 60 years of age who have served in the field of journalism in life.",
            "The state government has exempted agricultural version units from applying to the Agricultural Produce Market Committees for a license for direct purchase from farmers. He said that at present, there was a provision for obtaining direct purchase license from the agricultural processing units of the state by applying to the respective agricultural produce market committees for direct purchase from the farmers.",
            "Flour mills will be made available to the wheat mills by cutting a maximum of 100 grams per kg, that is, cutting 10 percent of the size, in lieu of the quantity of wheat to be given.",
            "The State Government has directed to put Rs. 824 crore under the material head into the account of the workers with immediate effect so that the labor item as well as the material item is not paid due to any inconvenience to the workers in such a critical time.",
            "The state government has extended the period of 5 percent interest subsidy scheme from tenure of March 31, 2020 to June 30, 2020, to tenants taking long-term agricultural loans, giving big relief to the farmers. Now tenants who repay the loan on time will get agricultural loan at 6.65 percent interest rate.",
            "Chief Minister Mr. Ashok Gehlot has instructed all school operators in the state not to take three months advance fee from the students till the lockdown applicable to prevent corona infection continues.",
            " Chief Minister Mr. Ashok Gehlot has announced to provide assistance of Rs. 50 lakhs to the dependent / family in case of untimely death due to corona infection while all the employees of the state government are on duty related to corona campaign.",
            "According to Chief Minister Mr. Ashok Gehlot, 5 lakh minikits of certified seeds of maize and 5 lakh minikits of certified seeds of millet of 1.5 kg are available free of cost to 5 lakh farmers in the Scheduled Tribes area of ​​the state during the Kharif season will be made.",
            "With the receipt of two months pension together, the amount of Rs 1500 and above will reach the hands of the beneficiaries of social security pension. This amount will be directly deposited in the bank accounts of the beneficiaries.The state government has already announced to give one rupee and two rupees per kg of wheat to the families covered under NFSA till May. A package of about 2 thousand crores has been prepared for all these."]
    Res={'picture':res,'state':res2,'name':res3,'pdf':res4,'news':res5}
    if request.method == "POST":
        url = "https://api.rootnet.in/covid19-in/stats/latest"
        response = requests.get(url) 
        regional=json.loads(response.text)['data']['regional']
        url = "https://api.rootnet.in/covid19-in/contacts"
        k = requests.get(url)
        url = "https://api.rootnet.in/covid19-in/stats/hospitals"
        r = requests.get(url)
        ur= "http://newsapi.org/v2/top-headlines?country=in&apiKey=0e230a7838364ca4baf2b954dc827c11"
        r2= requests.get(ur)
        myDict = request.form
        state = str(myDict['browsers'])
        # print(state)
        if state=="Rajasthan":
            for region in regional:
                if (region['loc'] == "Rajasthan"):
                    a=region['confirmedCasesIndian']
                    b=region['discharged']
                    c=region['deaths']
                    d=33
            res="Rajasthan-AshkokGehlot.jpg"
            res2="rajasthan.png"
            res3="Chief Minister Mr. Ashok Gehlot"
            res4=["https://www.mohfw.gov.in/pdf/GuidelinesfornotifyingCOVID-19affectedpersonsbyPrivateInstitutions.pdf",
    "https://www.mohfw.gov.in/pdf/DistrictWiseList324.pdf",
    "https://www.who.int/docs/default-source/wrindia/situation-report/india-situation-report-8.pdf?sfvrsn=cd671813_2",
    "https://education.rajasthan.gov.in/content/dam/doitassets/education/medicaleducation/sms-medical-college-jaipur/pdf/News/Govt.%20Instructions%20for%20COVID%2019.pdf",
    "https://cmrelief.rajasthan.gov.in/ContributionCovid-19.aspx"]
            res5=["During the lockdown period in the state, supply of essential commodities will be done from house to house, supply of ration items, dairy products, vegetables and medicines will be done.",
            "Important decisions of Chief Minister for relief from Corona crisis.Big relief to farmers, industries and general public Electricity and water bills will be postponed for two months.",
            "In view of the effect of Corona virus by the state government, it has been decided to provide whole grains in the form of pulses, wheat to about 35 lakh beneficiaries through Anganwadi centers. Under this, 35 lakh families will be given 1 kg of pulses and 2 to 3 kg of wheat in a month under Integrated Child Development Services Scheme. This system will remain in force for the duration of the corona virus.",
            "Immediate financial assistance of Rs.1000 / - deposited in the account of 15.78 lakh construction workers.",
            "Apart from this, 36 lakh 51 thousand BPL,Beneficiaries of State BPL and Antyodaya Yojana,25 lakh construction workers and registered street vendors who are not covered under Social Security Pension Scheme, will be given one thousand rupees as a fixed ex-gratia amount so that they will get cash in their hands and they can meet their daily needs. Will be able to do it.",
            "Shri Gehlot has instructed to give 2 months pension simultaneously to the beneficiaries of the state's 78 lakh social security pension.",
            "Due to the lock down by the state government, liquor license shops, hotel bars, restaurant bars have been given relief by deciding certain fee waivers and postponement.",
            "Chief Minister has given permission to extend the term of interest waiver scheme-2019 of Agricultural Marketing Department and extension of waiver of outstanding mandi duty on imported agricultural commodities and sugar for agricultural processing from outside the state till June 30.",
            "A monthly honorarium of Rs 5000 has been sanctioned to such senior journalists above 60 years of age who have served in the field of journalism in life.",
            "The state government has exempted agricultural version units from applying to the Agricultural Produce Market Committees for a license for direct purchase from farmers. He said that at present, there was a provision for obtaining direct purchase license from the agricultural processing units of the state by applying to the respective agricultural produce market committees for direct purchase from the farmers.",
            "Flour mills will be made available to the wheat mills by cutting a maximum of 100 grams per kg, that is, cutting 10 percent of the size, in lieu of the quantity of wheat to be given.",
            "The State Government has directed to put Rs. 824 crore under the material head into the account of the workers with immediate effect so that the labor item as well as the material item is not paid due to any inconvenience to the workers in such a critical time.",
            "The state government has extended the period of 5 percent interest subsidy scheme from tenure of March 31, 2020 to June 30, 2020, to tenants taking long-term agricultural loans, giving big relief to the farmers. Now tenants who repay the loan on time will get agricultural loan at 6.65 percent interest rate.",
            "Chief Minister Mr. Ashok Gehlot has instructed all school operators in the state not to take three months advance fee from the students till the lockdown applicable to prevent corona infection continues.",
            " Chief Minister Mr. Ashok Gehlot has announced to provide assistance of Rs. 50 lakhs to the dependent / family in case of untimely death due to corona infection while all the employees of the state government are on duty related to corona campaign.",
            "According to Chief Minister Mr. Ashok Gehlot, 5 lakh minikits of certified seeds of maize and 5 lakh minikits of certified seeds of millet of 1.5 kg are available free of cost to 5 lakh farmers in the Scheduled Tribes area of ​​the state during the Kharif season will be made.",
            "With the receipt of two months pension together, the amount of Rs 1500 and above will reach the hands of the beneficiaries of social security pension. This amount will be directly deposited in the bank accounts of the beneficiaries.The state government has already announced to give one rupee and two rupees per kg of wheat to the families covered under NFSA till May. A package of about 2 thousand crores has been prepared for all these."]
        elif state=="WestBengal":
            for region in regional:
                if (region['loc'] == "West Bengal"):
                    a=region['confirmedCasesIndian']
                    b=region['discharged']
                    c=region['deaths']
                    d=23
            res="WB-MamataBanerjee.jpg"
            res2="west bengal.jpg"
            res3="Chief Minister Mrs. Mamata Banerjee"
            res4=["https://www.mohfw.gov.in/pdf/GuidelinesfornotifyingCOVID-19affectedpersonsbyPrivateInstitutions.pdf",
    "https://www.mohfw.gov.in/pdf/DistrictWiseList324.pdf",
    "https://www.who.int/docs/default-source/wrindia/situation-report/india-situation-report-8.pdf?sfvrsn=cd671813_2",
    "https://www.wbhealth.gov.in/other_files/118.pdf",
    "https://www.wbhealth.gov.in/uploaded_files/corona/Serf.pdf",
    "https://wb.gov.in/upload/MCLNEWS-200322111116287.pdf"]
            res5=["West Bengal Chief Minister Mamata Banerjee announced setting up of dedicated nodal hospitals for COVID-19 cases in each of the 22 districts.",
            "West Bengal Chief Minister  also announced an increased insurance payout to all the government doctors, nurses, health workers, pharmacists, cleaning staffs, ASHA workers and anyone who has been aiding the government to curtail the pandemic from Rs 5 lakh to 10 lakh. The payout also includes sanitation workers and police personnel.",
            "Mamata Banerjee announces West Bengal WBBSE Class 12 exams will be held in June.The students of class 11 and colleges would be promoted to the next class and semesters",
            "Chief Minister Mamata Banerjee has promised some money for Bengal migrants stranded outside the state for petty expenses.",
            "The West Bengal government has decided to deploy armed police in parts of the state which have been identified as 'red zones'- the areas where Covid-19 is severe."]
        elif state=="TamilNadu":
            for region in regional:
                if (region['loc'] == "Tamil Nadu"):
                    a=region['confirmedCasesIndian']
                    b=region['discharged']
                    c=region['deaths']
                    d=32
            res="TN-EdappadiKPalaniswami.gif"
            res2="tamil nadu.png"
            res3="Chief Minister Mr. Edappadi K Palaniswami"
            res4=["https://www.mohfw.gov.in/pdf/GuidelinesfornotifyingCOVID-19affectedpersonsbyPrivateInstitutions.pdf",
    "https://www.mohfw.gov.in/pdf/DistrictWiseList324.pdf",
    "https://www.who.int/docs/default-source/wrindia/situation-report/india-situation-report-8.pdf?sfvrsn=cd671813_2",
    "https://www.manupatrafast.com/covid_19/Tamil%20Nadu/Govt/Corona%20Virus%20Disease%20COVID19%20Infection%20Prevention%20and%20Control%20The%20Epidemic%20Diseases%20Act%201897.pdf",
    "http://www.hcmadras.tn.nic.in/COVID%2019_Administrative_directives_for_high_court.pdf",
    "https://ereceipt.tn.gov.in/cmprf/cmprf.html"]
            res5=["The Tamil Nadu government has ordered private banks, small finance banks and micro lenders to refrain from collecting loan repayments till further notice, raising the spectre of other states following suit that could create chaos and defaults.",
            "Tamil Nadu Chief Minister K Palaniswami on Tuesday announced sops to farmers, which include waiver of cold storage user fee and facilitation in marketing and logistics. Besides, loans upto Rs 10 lakh would be offered to farmer producer firms aimed at benefiting both ryots and consumers.",
            "The Tamil Nadu government made an announcement restricting volunteers and NGOs from distributing relief materials to people affected by the COVID-19 pandemic.",
            "Tamil Nadu CM writes to PM Modi, seeks funds to combat COVID-19.",
            "Tamil Nadu government announces one month of special pay to doctors, nurses and sanitation workers.",
            "The Chief Minister said all the ration card-holders would get ₹1000 and free rice, dal, cooking oil and sugar.",
            "Construction workers and autorickshaw drivers who are members of the welfare board would get ₹1000, 15 kg of rice, 1 kg of dal and 1 kg of cooking oil.",
            "For the elderly who depend on Anganwardis, the food will be distributed directly to them in their homes. For platform vendors who have registered with the government, an additional ₹1000 will be given.",
            "Those who are working under Mahatma Gandhi Rural Employment Scheme will get a two-day special salary.",
            "The government will provide a job to one person from such victims' families on compassionate grounds and based on their qualification, he said.",
            "The CM said that the  work of medical professionals in both public and private sectors and others will be recognised and such persons will be provided with awards, he said."]
        elif state=="MadhyaPradesh":
            for region in regional:
                if (region['loc'] == "Madhya Pradesh"):
                    a=region['confirmedCasesIndian']
                    b=region['discharged']
                    c=region['deaths']
                    d=52
            res="MP-ShivrajSinghChouhan.jpg"
            res2="madhya pradesh.png"
            res3="Chief Minister Mr. Shivraj Singh Chouhan"
            res4=["https://www.mohfw.gov.in/pdf/GuidelinesfornotifyingCOVID-19affectedpersonsbyPrivateInstitutions.pdf",
    "https://www.mohfw.gov.in/pdf/DistrictWiseList324.pdf",
    "https://www.who.int/docs/default-source/wrindia/situation-report/india-situation-report-8.pdf?sfvrsn=cd671813_2"]
            res5=["The state's control and command centre has been established where arrangements have been made for providing telemedicine facilities to people. Doctors are deployed and are answering people's calls, prescribing medicines and planning for providing them.",
            "The Chief Minister has also decided to provide ration free of cost to the poor, irrespective of whether they have ration cards or not, for the next three months.",
            "The Madhya Pradesh government has decided to promote students of all classes, except standards 10th and 12th of the state schools without exams in view of the coronavirus outbreak.",
            "Around 1,500 beds have been arranged at Bhopal and Indore for treatment of corona-affected patients.",
            "MP govt makes wearing masks mandatory while stepping out",
            " ₹1,000 would be provided to labourers as support through the State Building and Other Construction Workers Welfare Board.",
            " Mr. Chouhan announced ₹2,000 for two months to each Saharia, Baiga and Bharia family.",
            "Old age and social security pensioners would receive an advance amount of ₹1,200 for two months."]
        elif state=="Jharkhand":
            for region in regional:
                if (region['loc'] == "Jharkhand"):
                    a=region['confirmedCasesIndian']
                    b=region['discharged']
                    c=region['deaths']
                    d=24
            res="Jharkhand-HemantSoren.jpg"
            res2="jharkhand.png"
            res3="Chief Minister Mr. Hemant Soren"
            res4=["https://www.mohfw.gov.in/pdf/GuidelinesfornotifyingCOVID-19affectedpersonsbyPrivateInstitutions.pdf",
    "https://www.mohfw.gov.in/pdf/DistrictWiseList324.pdf",
    "https://www.who.int/docs/default-source/wrindia/situation-report/india-situation-report-8.pdf?sfvrsn=cd671813_2",
    "https://cdn.s3waas.gov.in/s32b8a61594b1f4c4db0902a8a395ced93/uploads/2020/03/2020032080.pdf"]
            res5=["As a significant portion of Jharkhand’s population is employed as migrant workers in other states, the Chief Minister, Hemant Soren has been working with his counterparts to bring back any person of the state who is stranded elsewhere in the country",
            "Rs. 200 crores have been earmarked towards expenditure in connection with the tests and setting up of other apparatus to deal with any emerging situation.",
            "The Jharkhand government has set up a 557-bed isolation ward for COVID-19 patients in different hospitals of the state. The state has also set up 1,469 quarantine centres at different places.",
            "Jharkhand Chief Minister Hemant Soren said his government has decided to provide two months ration in advance to the beneficiaries of the Public Distribution System, as the country entered the second day of the lockdown to contain the coronavirus outbreak.",
            "The state government of Jharkhand needs urgent help from the Centre, said CM Hemant Soren, as Jharkhand is a small, poor and backward state and at the early stage the coronavirus can be contained under control.",
            "17 districts are to be declared as green zones by the Jharkhand state government as no COVID-19 patients came from these states.",
            "The Jharkhand government for the first time has come up with an innovative idea to open up 600 dal bhat kendra in police stations to feed the poor. The poor will have access to meals at a throw-away price of Rs 5 in police stations.",
            "Jharkhand Chief Minister Hemant Soren said his government has decided to provide two months ration in advance to the beneficiaries of the public distribution system, as the country entered the lockdown to contain the COVID-19 outbreak.",
            "More than 350 khichidi centres will start functioning so that the needy can have meals, Chief Minister Hemant Soren announced amidst the pandemic.",
            "Jharkhand Chief Minister Hemant Soren on Sunday dismissed the government’s statements that an event organised by the Tablighi Jamaat in Delhi’s Nizamuddin area has led to a big jump in coronavirus infections in India.",
            "The Jharkhand state government issued a rate-chart of essential items at the public distribution outlets, following reports that a few ration shops were charging higher prices for commodities and some others selling them in the black market, taking advantage of the lockdown.",
            "Ranchi Deputy Commissioner Rai Mahimapat Ray said that the administration has launched an app - VeggiGo - for people to place online orders for essential commodities."]
        elif state=="Maharashtra":
            for region in regional:
                if (region['loc'] == "Maharashtra"):
                    a=region['confirmedCasesIndian']
                    b=region['discharged']
                    c=region['deaths']
                    d=36
            res="Maharashtra-UddhavThackeray.jpg"
            res2="maharashtra.png"
            res3="Chief Minister Mr. Uddhav Thackeray"
            res4=["https://www.mohfw.gov.in/pdf/GuidelinesfornotifyingCOVID-19affectedpersonsbyPrivateInstitutions.pdf",
    "https://www.mohfw.gov.in/pdf/DistrictWiseList324.pdf",
    "https://www.who.int/docs/default-source/wrindia/situation-report/india-situation-report-8.pdf?sfvrsn=cd671813_2",
    "https://www.maharashtra.gov.in/Site/Upload/Acts%20Rules/Marathi/Korona%20Notification%2014%20March%202020....pdf"]
            res5=["Uddhav Thackeray, Maharashtra CM promised the migrants of all possible help- food, shelter and necessary medication. Thackeray also urged the migrants to stay in their current locations and assured them of necessary arrangements once the situation improves.",
            "Relief camps have been set up all over the Maharashtra sheltering more than 5 lakh labourers.",
            "Maharashtra government  instructed all the landlords and house owners in the state to postpone rent collection by at least three months to help those whose income has been affected due to coronavirus lockdown.",
            "More than 1 crore people have been benefited through the PDS distribution of rice.",
            "2 task force has been deployed to work on the economic front.",
            "Wearing mask/cloth in public is made mandatory.",
            "There are no restrictions on agricultural activities, agricultural commodities like seeds, fertilizers or farming tools.",
            "The authorities are instructed to distribute essentials to tribal people who reside in remote and hilly terrains before beginning of monsoon."]
        elif state=="AndhraPradesh":
            for region in regional:
                if (region['loc'] == "Andhra Pradesh"):
                    a=region['confirmedCasesIndian']
                    b=region['discharged']
                    c=region['deaths']
                    d=13
            res="AP-YSJaganmohanReddy.jpeg"
            res2="ap.png"
            res3="Chief Minister Mr. Y S Jaganmohan Reddy"
            res4=["https://www.mohfw.gov.in/pdf/GuidelinesfornotifyingCOVID-19affectedpersonsbyPrivateInstitutions.pdf",
    "https://www.mohfw.gov.in/pdf/DistrictWiseList324.pdf",
    "https://www.who.int/docs/default-source/wrindia/situation-report/india-situation-report-8.pdf?sfvrsn=cd671813_2"]
            res5=["The State government  decided to supply ration to every poor family as food security.",
            "The social security pensions to beneficiaries and `1,000 aid to the poor people will be door delivered through village/ward volunteers.",
            "Andhra Pradesh was the first state to undertake a state-wide rollout of both laboratory and radiology services using a hybrid model.",
            "The Andhra Pradesh government has decided to take over facilities and manpower at private hospitals by invoking powers under the Disaster Management Act to tackle the Covid-19 pandemic.",
            "Reviewing the agriculture and horticulture situation in the State with higher officials , the Chief Minister said the government is gearing up for setting up YSR Janata Bazaars to help both farmers and consumers.CM says 22,000 Janata Bazaars with cold storage units and small and midsize trucks for transportation of farm produce, will create a robust marketing network to boost the income of farmers and others dependent on agriculture and allied sectors.",
            " Andhra Pradesh CM YS Jaganmohan Reddy has announced that the government will provide financial assistance of Rs 2,000 to every poor person who has completed quarantine in government facilities. If the coronavirus test is negative after completing quarantine, then after quarantine, every poor person will be given Rs 2000 so that he can buy nutritious food.",
            "Andhra Pradesh announces one-time aid of Rs 2,000 to 6,000 fishermen struck in Gujarat due to lockdown",
            "The Andhra Pradesh government  announced deferment of payment of full salaries to the chief minister, officers and employees, saying its revenue streams have totally dried up in view of the ongoing lockdown to combat coronavirus.",
            "The  government have made wearing face mask compulsory.",
            "Chief Minister Y.S. Jagan Mohan Reddy on Monday made a two-stage strategy to check the spread of COVID-19 with particular emphasis on urban areas in the State."]
        elif state=="Chattisgarh":
            for region in regional:
                if (region['loc'] == "Chhattisgarh"):
                    a=region['confirmedCasesIndian']
                    b=region['discharged']
                    c=region['deaths']
                    d=27
            res="Chhattisgarh-BhupeshBaghel.jpg"
            res2="chhattisgarh.png"
            res3="Chief Minister Mr. Bhupesh Baghel"
            res4=["https://www.mohfw.gov.in/pdf/GuidelinesfornotifyingCOVID-19affectedpersonsbyPrivateInstitutions.pdf",
    "https://www.mohfw.gov.in/pdf/DistrictWiseList324.pdf",
    "https://www.who.int/docs/default-source/wrindia/situation-report/india-situation-report-8.pdf?sfvrsn=cd671813_2"]
            res5=["The Chhattisgarh government will provide 100 beds each to all of 28 districts of the state to deal with coronavirus cases",
            "Raipur Institute of Medical Sciences College is being developed as a COVID-19 hospital where 500 beds are being arranged.",
            "In a bid to provide relief to the poor amid an ongoing coronavirus pandemic, the Chhattisgarh government issued a notification stating that rice and dal will be distributed to ration card holders, students and others.",
            "School children will also be allocated their quota of grains as per the mid-day meal scheme during the holiday period. Under this scheme, 40 days dry dal and rice will be supplied to the children of the school. Each student of primary school will be given 4 kg of rice and 800 grams of dal and 6 kg of rice and 1,200 grams of dal will be distributed to each student of higher secondary schools.",
            "As a goodwill gesture, the Chhattisgarh government has decided to give special allowance to the staff of the Health Department engaged in the treatment of the coronavirus.",
            "The government has licensed two distilleries for industrial manufacture of alcohol-based hand sanitisers as part of measures to prevent coronavirus",
            "The time limit for renewal of a licence or permit has also been increased by a month in all urban bodies of the state. The last date for depositing property tax in urban bodies of the state has been extended from March 31 to April 30",
            "The state government is also initiating a drive to use drones for spraying of disinfectants in areas which could be prone to coronavirus spread such as the vicinity of hospitals, marketplaces with concentration of food, medical and essential commodity stores etc. "]
        elif state=="Delhi":
            for region in regional:
                if (region['loc'] == "Delhi"):
                    a=region['confirmedCasesIndian']
                    b=region['discharged']
                    c=region['deaths']
                    d=11
            res="Delhi-ArvindKejriwal.jpg"
            res2="delhi.png"
            res3="Chief Minister Mr. Arvind Kejriwal"
            res4=["https://www.mohfw.gov.in/pdf/GuidelinesfornotifyingCOVID-19affectedpersonsbyPrivateInstitutions.pdf",
    "https://www.mohfw.gov.in/pdf/DistrictWiseList324.pdf",
    "https://www.who.int/docs/default-source/wrindia/situation-report/india-situation-report-8.pdf?sfvrsn=cd671813_2",
    "http://health.delhigovt.nic.in/wps/wcm/connect/146af7004d87f91c96a2f7982ee7a5c7/Epedimic+Act.pdf?MOD=AJPERES&lmod=-762064770&CACHEID=146af7004d87f91c96a2f7982ee7a5c7",
    "https://main.sci.gov.in/pdf/cir/covid19_14032020.pdf",
    "https://www.icmr.nic.in/sites/default/files/Circulars_front/Dopt_order1.pdf"]
            res5=["Delhi Chief Minister Arvind Kejriwal announced a 5T plan to stop the spread of the novel coronavirus. The 5T plan includes testing, tracing, treatment, teamwork and tracking.",
            "Delhi government is  also distributing food in over 500 schools and 238 night shelters.” The  568 schools have been converted into community kitchens to prepare food for the needy.",
            "Delhi CM Arvind Kejriwal  also announced  that families of healthcare personnel will get Rs 1 crore in case they die while dealing with coronavirus cases.",
            "Delhi government have made wearing face mask compulsory.",
            "The Delhi government will takeover 12,000 hotel rooms in the wake of spike in number of coronavirus cases, Kejriwal said.",
            "Delhi Chief Minister Arvind Kejriwal on Thursday announced an comprehensive plan named 'SHIELD' to control the spread of coronavirus in the national capital.S stands for sealing of localities, wherein, people from a locality will not go to other areas and vice-versa.   H means home quarantine i.e. people will remain in their homes only.  I stands for isolation and tracing under which COVID-19 patients will be isolated and people whom they have met will be traced, identified and will be isolated too.  E means essential supplies under which we will ensure door to door delivery of essential services.  L refers to local sanitisation under which areas will be disinfected on a regular basis.  While D stands for door-to-door checking under which we will ask every family whether there is any person having symptoms of coronavirus. If any such person is found, their samples will be taken and further procedure will be followed.",
            "The Delhi government will give 2,000 food coupons each to every MLA and MP in the city for distribution among the needy in their constituencies amid the coronavirus-induced lockdown.",
            "Government has also decided to provide free-ration to around 30 lakh people who do not have ration cards, asserting that there are currently 71 lakh beneficiaries of public distribution system and they are being provided free-ration by the government.",
            "Government will also distribute kits of daily-use items such as soap, oil, sugar, turmeric and salt along with ration for the month of May.",
            "The Delhi government will procure 60 new ambulances and an order has been issued for the same"
            ]
        elif state=="Bihar":
            for region in regional:
                if (region['loc'] == "Bihar"):
                    a=region['confirmedCasesIndian']
                    b=region['discharged']
                    c=region['deaths']
                    d=38
            res="1587169330081_Bihar-NitishKumar.jpg"
            res2="bihar.png"
            res3="Chief Minister Mr. Nitish Kumar"
            res4=["https://www.mohfw.gov.in/pdf/GuidelinesfornotifyingCOVID-19affectedpersonsbyPrivateInstitutions.pdf",
    "https://www.mohfw.gov.in/pdf/DistrictWiseList324.pdf",
    "https://www.who.int/docs/default-source/wrindia/situation-report/india-situation-report-8.pdf?sfvrsn=cd671813_2",
    "http://egazette.bih.nic.in/GazettePublished/212_2_2020.pdf",
    "http://www.cmrf.bih.nic.in/users/home.aspx"]
            res5=["Bihar State Disaster Management Department said a total of 14.56 lakh migrants have been provided relief so far in camps set up for the purpose in Delhi and Mumbai, and states like Tamil Nadu, Karnataka and West Bengal.",
            "The process of sending show-cause notices to another 122 doctors too has been started and will be issued against them soon”, said a senior Health Department Official.",
            "The State government on Monday announced that financial assistance of ₹1,000 was credited to the bank accounts of 1.03 lakh migrant workers of Bihar working in other States, through DBT (Direct Benefit Transfer) system.",
            " Chief Minister Nitish Kumar (CM Nitish Kumar) has announced to give one month's basic salary as a separate incentive amount to the doctors and other medical workers and also as a package of assistance to the pensioners from ration card holders to large Relieved.",
            "The Bihar government has also announced one month ration free to all ration card holders families.",
            "Chief Minister Nitish Kumar on Monday launched a scheme to provide special assistance of Rs 1,000 each to the natives of Bihar stranded in other states due to the nationwide lockdown prompted by coronavirus",
            "Apart from the ration cardholders, the Bihar chief minister has also asked the state Disaster Management officials to reach out to every state resident stranded outside Bihar because of the ongoing lockdown.",
            "The Bihar chief minister said Rs 100 crore has been released from the Chief Minister's Relief Fund for setting up shelters for rickshaw pullers and daily-wage earners.",
            "Also, three months pension will be given immediately in advance to all under old age pension, disabled pension, widow pension and old age pension. If this amount will be directly deposited in their account, then one thousand rupees per family will be given to all the ration card holder families in the panchayat of lockdown area and block headquarters. The amount will be sent to their account.",
            "Along with this, Chief Minister Nitish Kumar has also made a big announcement for the students and said that all students from 1st to 12th will be given scholarships.",
            "Chief Minister Nitish Kumar on Monday launched a scheme to provide special assistance of Rs 1,000 each to the natives of Bihar stranded in other states due to the nationwide lockdown prompted by coronavirus.",
            "Apart from the ration cardholders, the Bihar chief minister has also asked the state Disaster Management officials to reach out to every state resident stranded outside Bihar because of the ongoing lockdown.",
            "The Bihar chief minister said Rs 100 crore has been released from the Chief Minister's Relief Fund for setting up shelters for rickshaw pullers and daily-wage earners."]
        elif state=="UttarPradesh":
            for region in regional:
                if (region['loc'] == "Uttar Pradesh"):
                    a=region['confirmedCasesIndian']
                    b=region['discharged']
                    c=region['deaths']
                    d=75
            res="UP-YogiAdityanath.jpg"
            res2="uttar pradesh.png"
            res3="Chief Minister Mr. Yogi Adityanath"
            res4=["https://www.mohfw.gov.in/pdf/GuidelinesfornotifyingCOVID-19affectedpersonsbyPrivateInstitutions.pdf",
    "https://www.mohfw.gov.in/pdf/DistrictWiseList324.pdf",
    "https://www.who.int/docs/default-source/wrindia/situation-report/india-situation-report-8.pdf?sfvrsn=cd671813_2"]
            res5=["The Uttar Pradesh government transferred Rs 1,000 each to the bank accounts of daily wagers, including street vendors and rickshaw pullers, as maintenance allowance for the COVID-19 lockdown period.",
            "The Yogi Adityanath government in Uttar Pradesh has decided to completely seal  15 districts that have emerged as coronavirus hotspots. The districts to be sealed are Lucknow, Noida, Ghaziabad, Sitapur, Kanpur, Agra, Ferozabad, Bareilly, Meerut, Shamli, Saharanpur, Bulandshahr, Varanasi, Maharajganj and Basti.",
            "The Uttar Pradesh government allows migrant workers to go home after completing the quarantine period.",
            "Uttar Pradesh government bans gutkha to avoid COVID-19 spread.",
            "Uttar Pradesh has obtained clearance from the Indian Council for Medical Research (ICMR) to start pool testing becoming the first in the country to attempt this method which aims at expediting the testing process for Covid-19.",
            "The Uttar Pradesh Government has roped in professional counsellors to counsel people who are in quarantine centres, shelter homes and even people at home. "]
        Res={'picture':res,'state':res2,'name':res3,'pdf':res4,'news':res5}
        
        return render_template('examples/map.html',a=a,b=b,c=c,d=d, inf=Res, regional=json.loads(response.text)['data']['regional'],rg=json.loads(k.text)['data']['contacts']['regional'], hs=json.loads(r.text)['data']['regional'],news=json.loads(r2.text)['articles'],overall=json.loads(response.text)['data']['summary'])
    return render_template('examples/map.html',a=a,b=b,c=c,d=d,inf=Res,regional=json.loads(response.text)['data']['regional'],rg=json.loads(k.text)['data']['contacts']['regional'], hs=json.loads(r.text)['data']['regional'],news=json.loads(r2.text)['articles'],overall=json.loads(response.text)['data']['summary'])



@app.route('/abc.html',methods=['GET', 'POST'])
@cache.cached(timeout=120)
def create_figure():
    ConfirmedCases_raw=pd.read_csv('https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv')
    Deaths_raw=pd.read_csv('https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_deaths_global.csv')
    Recoveries_raw=pd.read_csv('https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_recovered_global.csv')
    def cleandata(df_raw):
        df_cleaned=df_raw.melt(id_vars=['Province/State','Country/Region','Lat','Long'],value_name='Cases',var_name='Date')
        df_cleaned=df_cleaned.set_index(['Country/Region','Province/State','Date'])
        return df_cleaned 
# Clean all datasets
    ConfirmedCases=cleandata(ConfirmedCases_raw)
    Deaths=cleandata(Deaths_raw)
    Recoveries=cleandata(Recoveries_raw)

    ### Get Countrywise Data
    def countrydata(df_cleaned,oldname,newname):
        df_country=df_cleaned.groupby(['Country/Region','Date'])['Cases'].sum().reset_index()
        df_country=df_country.set_index(['Country/Region','Date'])
        df_country.index=df_country.index.set_levels([df_country.index.levels[0], pd.to_datetime(df_country.index.levels[1])])
        df_country=df_country.sort_values(['Country/Region','Date'],ascending=True)
        df_country=df_country.rename(columns={oldname:newname})
        return df_country
  
    ConfirmedCasesCountry=countrydata(ConfirmedCases,'Cases','Total Confirmed Cases')
    DeathsCountry=countrydata(Deaths,'Cases','Total Deaths')
    RecoveriesCountry=countrydata(Recoveries,'Cases','Total Recoveries')

### Get DailyData from Cumulative sum
    def dailydata(dfcountry,oldname,newname):
        dfcountrydaily=dfcountry.groupby(level=0).diff().fillna(0)
        dfcountrydaily=dfcountrydaily.rename(columns={oldname:newname})
        return dfcountrydaily

    NewCasesCountry=dailydata(ConfirmedCasesCountry,'Total Confirmed Cases','Daily New Cases')
    NewDeathsCountry=dailydata(DeathsCountry,'Total Deaths','Daily New Deaths')
    NewRecoveriesCountry=dailydata(RecoveriesCountry,'Total Recoveries','Daily New Recoveries')


    CountryConsolidated=pd.merge(ConfirmedCasesCountry,NewCasesCountry,how='left',left_index=True,right_index=True)
    CountryConsolidated=pd.merge(CountryConsolidated,NewDeathsCountry,how='left',left_index=True,right_index=True)
    CountryConsolidated=pd.merge(CountryConsolidated,DeathsCountry,how='left',left_index=True,right_index=True)
    CountryConsolidated=pd.merge(CountryConsolidated,RecoveriesCountry,how='left',left_index=True,right_index=True)
    CountryConsolidated=pd.merge(CountryConsolidated,NewRecoveriesCountry,how='left',left_index=True,right_index=True)
    CountryConsolidated['Active Cases']=CountryConsolidated['Total Confirmed Cases']-CountryConsolidated['Total Deaths']-CountryConsolidated['Total Recoveries']
    CountryConsolidated['Share of Recoveries - Closed Cases']=np.round(CountryConsolidated['Total Recoveries']/(CountryConsolidated['Total Recoveries']+CountryConsolidated['Total Deaths']),2)
    CountryConsolidated['Death to Cases Ratio']=np.round(CountryConsolidated['Total Deaths']/CountryConsolidated['Total Confirmed Cases'],3)
    TotalCasesCountry=CountryConsolidated.max(level=0)['Total Confirmed Cases'].reset_index().set_index('Country/Region')
    TotalCasesCountry=TotalCasesCountry.sort_values(by='Total Confirmed Cases',ascending=False)
    TotalCasesCountryexclChina=TotalCasesCountry[~TotalCasesCountry.index.isin(['Mainland China','Others'])]
    Top10countriesbycasesexclChina=TotalCasesCountryexclChina.head(10)
    TotalCasesCountrytop10=TotalCasesCountry.head(10)
    fig = go.Figure(go.Bar(x=Top10countriesbycasesexclChina.index, y=Top10countriesbycasesexclChina['Total Confirmed Cases'],
                        text=Top10countriesbycasesexclChina['Total Confirmed Cases'],
                textposition='outside'))
    fig.update_layout(title_text='Top 10 Countries by Total Confirmed Cases')
    fig.update_yaxes(showticklabels=False)
    
    py.offline.plot(fig, filename='templates/abc.html',auto_open=False)
    return render_template('abc.html')
     
# @app.route('/map.html')
# def mp():



@app.route('/plot2.html',methods=['GET', 'POST'])
@cache.cached(timeout=120)
def create_figure2():
    ConfirmedCases_raw=pd.read_csv('https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv')
    Deaths_raw=pd.read_csv('https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_deaths_global.csv')
    Recoveries_raw=pd.read_csv('https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_recovered_global.csv')

    def cleandata(df_raw):
        df_cleaned=df_raw.melt(id_vars=['Province/State','Country/Region','Lat','Long'],value_name='Cases',var_name='Date')
        df_cleaned=df_cleaned.set_index(['Country/Region','Province/State','Date'])
        return df_cleaned 

    # Clean all datasets
    ConfirmedCases=cleandata(ConfirmedCases_raw)
    Deaths=cleandata(Deaths_raw)
    Recoveries=cleandata(Recoveries_raw)


    ### Get Countrywise Data
    def countrydata(df_cleaned,oldname,newname):
        df_country=df_cleaned.groupby(['Country/Region','Date'])['Cases'].sum().reset_index()
        df_country=df_country.set_index(['Country/Region','Date'])
        df_country.index=df_country.index.set_levels([df_country.index.levels[0], pd.to_datetime(df_country.index.levels[1])])
        df_country=df_country.sort_values(['Country/Region','Date'],ascending=True)
        df_country=df_country.rename(columns={oldname:newname})
        return df_country
    
    ConfirmedCasesCountry=countrydata(ConfirmedCases,'Cases','Total Confirmed Cases')
    DeathsCountry=countrydata(Deaths,'Cases','Total Deaths')
    RecoveriesCountry=countrydata(Recoveries,'Cases','Total Recoveries')

    ### Get DailyData from Cumulative sum
    def dailydata(dfcountry,oldname,newname):
        dfcountrydaily=dfcountry.groupby(level=0).diff().fillna(0)
        dfcountrydaily=dfcountrydaily.rename(columns={oldname:newname})
        return dfcountrydaily

    NewCasesCountry=dailydata(ConfirmedCasesCountry,'Total Confirmed Cases','Daily New Cases')
    NewDeathsCountry=dailydata(DeathsCountry,'Total Deaths','Daily New Deaths')
    NewRecoveriesCountry=dailydata(RecoveriesCountry,'Total Recoveries','Daily New Recoveries')

    CountryConsolidated=pd.merge(ConfirmedCasesCountry,NewCasesCountry,how='left',left_index=True,right_index=True)
    CountryConsolidated=pd.merge(CountryConsolidated,NewDeathsCountry,how='left',left_index=True,right_index=True)
    CountryConsolidated=pd.merge(CountryConsolidated,DeathsCountry,how='left',left_index=True,right_index=True)
    CountryConsolidated=pd.merge(CountryConsolidated,RecoveriesCountry,how='left',left_index=True,right_index=True)
    CountryConsolidated=pd.merge(CountryConsolidated,NewRecoveriesCountry,how='left',left_index=True,right_index=True)
    CountryConsolidated['Active Cases']=CountryConsolidated['Total Confirmed Cases']-CountryConsolidated['Total Deaths']-CountryConsolidated['Total Recoveries']
    CountryConsolidated['Share of Recoveries - Closed Cases']=np.round(CountryConsolidated['Total Recoveries']/(CountryConsolidated['Total Recoveries']+CountryConsolidated['Total Deaths']),2)
    CountryConsolidated['Death to Cases Ratio']=np.round(CountryConsolidated['Total Deaths']/CountryConsolidated['Total Confirmed Cases'],3)


    ## Get totals for all metrics
    GlobalTotals=CountryConsolidated.reset_index().groupby('Date').sum()
    GlobalTotals['Share of Recoveries - Closed Cases']=np.round(GlobalTotals['Total Recoveries']/(GlobalTotals['Total Recoveries']+GlobalTotals['Total Deaths']),2)
    GlobalTotals['Death to Cases Ratio']=np.round(GlobalTotals['Total Deaths']/GlobalTotals['Total Confirmed Cases'],3)
    GlobalTotals.tail(2)

    # Create Plots that show Key Metrics For the Covid-19
    chartcol='red'
    fig = make_subplots(rows=3, cols=2,shared_xaxes=True,
                        specs=[[{}, {}],[{},{}],
                        [{"colspan": 2}, None]],
                        subplot_titles=('Confirmed Cases','Active Cases','Deaths','Recoveries','Death to Cases Ratio'))
    fig.add_trace(go.Scatter(x=GlobalTotals.index,y=GlobalTotals['Total Confirmed Cases'],
                            mode='lines+markers',
                            name='Confirmed Cases',
                            line=dict(color=chartcol,width=2)),
                            row=1,col=1)
    fig.add_trace(go.Scatter(x=GlobalTotals.index,y=GlobalTotals['Active Cases'],
                            mode='lines+markers',
                            name='Active Cases',
                            line=dict(color=chartcol,width=2)),
                            row=1,col=2)
    fig.add_trace(go.Scatter(x=GlobalTotals.index,y=GlobalTotals['Total Deaths'],
                            mode='lines+markers',
                            name='Deaths',
                            line=dict(color=chartcol,width=2)),
                            row=2,col=1)
    fig.add_trace(go.Scatter(x=GlobalTotals.index,y=GlobalTotals['Total Recoveries'],
                            mode='lines+markers',
                            name='Recoveries',
                            line=dict(color=chartcol,width=2)),
                            row=2,col=2)
    fig.add_trace(go.Scatter(x=GlobalTotals.index,y=GlobalTotals['Death to Cases Ratio'],
                            mode='lines+markers',
                            line=dict(color=chartcol,width=2)),
                            row=3,col=1)
    fig.update_layout(showlegend=False)
    fig.update_layout(title_text='Global Statistics')

    py.offline.plot(fig, filename='templates/plot2.html',auto_open=False)
    return render_template('plot2.html')

@app.route('/team')
@cache.cached(timeout=50)
def team():
    return render_template('team/team.html')

@app.route('/faq')
@cache.cached(timeout=50)
def faq():
    return render_template('examples/upgrade.html')


@app.route('/facts')
@cache.cached(timeout=50)
def facts():
    return render_template('examples/facts.html')


    
if __name__ == '__main__':
    
    app.jinja_env.auto_reload = True
    app.run(debug=False,threaded =True)
