#Les biliothèques
library(shiny)
library(shinyWidgets)
library(shinydashboard)
library(shinythemes)
library(magrittr)
library(rvest)
library(dplyr)
library(ggplot2)
library(scales)
library(lubridate)
library(FactoMineR)
library(factoextra)
library(tibble) #pour utiliser la fonction column_to_rownames
library(tidyverse) #pour utiliser %>%
library(RCurl) #pour pouvoir lire les csv brut sur le lien
#pour la carte
library(geojsonio)
library(maps)
library(leaflet)

#la carte du monde
worldcountry = geojson_read("/Users/ingdavid/Documents/2A/2IA/Validation/TP_Projet/datasets/countries.geo.json", what = "sp")

# 3 lettres répresente chaque pays 
alpha3= c(worldcountry$id,"CAM","ABW","AIA","ALA","AND","ASM","ATG","BEF","BHR","BLM")
cv_large_countries = cv_today %>% filter(alpha3 %in% worldcountry$id)

# import data directement sur le lien en github
cv <-  read.csv(text = getURL("https://raw.githubusercontent.com/eparker12/nCoV_tracker/master/input_data/coronavirus.csv"))
cv_global <- read.csv(text = getURL("https://raw.githubusercontent.com/eparker12/nCoV_tracker/master/input_data/coronavirus_global.csv"))
cv_states <- read.csv(text = getURL("https://raw.githubusercontent.com/eparker12/nCoV_tracker/master/input_data/coronavirus_states.csv"))
cv_continent <- read.csv(text = getURL("https://raw.githubusercontent.com/eparker12/nCoV_tracker/master/input_data/coronavirus_continent.csv"))
cv_today <- read.csv(text = getURL("https://raw.githubusercontent.com/eparker12/nCoV_tracker/master/input_data/coronavirus_today.csv"))
countries <- read.csv(text = getURL("https://raw.githubusercontent.com/eparker12/nCoV_tracker/master/input_data/countries_codes_and_coordinates.csv"))

# check consistency of country names across datasets
if (all(unique(cv$country) %in% unique(countries$country))==FALSE) { print("Error: inconsistent country names")}

# extract dates from cv data
if (any(grepl("/", cv$date))) { 
  cv$date = format(as.Date(cv$date, format="%d/%m/%Y"),"%Y-%m-%d") 
} else { cv$date = as.Date(cv$date, format="%Y-%m-%d") }

# merge cv data with country data and extract key summary variables
cv = merge(cv, countries %>% select(-c(jhu_ID, global_level, continent_level)), by = "country")
cv = cv[order(cv$date),]


# créer des paramètres pour tracer la carte
bins = c(0,1,10,50,100,500)
cv_pal <- colorBin("Oranges", domain = cv_large_countries$per100k, bins = bins)
plot_map <- worldcountry[worldcountry$id %in% cv_large_countries$alpha3, ]
cv$date = as.Date(cv$date)
cv_min_date = as.Date(min(cv$date),"%Y-%m-%d")
current_date = as.Date(max(cv$date),"%Y-%m-%d")
cv_max_date_clean = format(as.POSIXct(current_date),"%d %B %Y")

#filtre les pays avec nombre total de cas plus de 10000
cv_today_cases_10000 = subset(cv_today, cases>10000)
#filtre les pays avec nombre total de cas plus de 10000
cv_today_deaths_10000 = subset(cv_today, deaths>10000)
#filtre les pays avec nombre total de cas et de décès plus de 10000
cv_cas_décès_10000 = subset(cv_today_cases_10000,deaths>10000)
#list des pays
country_list <- cv %>% distinct(jhu_ID, .keep_all = TRUE) %>% arrange(jhu_ID)

# somme cv compte par date
cv_aggregated = aggregate(cv$cases, by=list(Category=cv$date), FUN=sum)
cv_aggregated_China = aggregate(subset(cv, country=="Mainland China")$cases, by=list(Category=subset(cv, country=="Mainland China")$date), FUN=sum)
cv_aggregated_other = aggregate(subset(cv, country!="Mainland China")$cases, by=list(Category=subset(cv, country!="Mainland China")$date), FUN=sum)
names(cv_aggregated) = names(cv_aggregated_China) = names(cv_aggregated_other) = c("date", "cases")

# ajouter variable pour nouveaux cas en dernier 24 hours
for (i in 1:nrow(cv_aggregated)) { 
  if (i==1) { cv_aggregated$new[i] = cv_aggregated_China$new[i] = cv_aggregated_other$new[i] = NA }
  if (i>1) { 
    cv_aggregated$new[i] = cv_aggregated$cases[i] - cv_aggregated$cases[i-1] 
    cv_aggregated_China$new[i] = cv_aggregated_China$cases[i] - cv_aggregated_China$cases[i-1] 
    cv_aggregated_other$new[i] = cv_aggregated_other$cases[i] - cv_aggregated_other$cases[i-1] 
  }
}

# ajouter les régions
cv_aggregated$region = "Global"
cv_aggregated_China$region = "Mainland China"
cv_aggregated_other$region = "Other"
cv_aggregated = rbind(cv_aggregated, cv_aggregated_China, cv_aggregated_other)
cv_aggregated$region = factor(cv_aggregated$region, levels=c("Global", "Mainland China", "Other"))



#Shiny UI
bootstrapPage(
  navbarPage(theme = shinytheme("sandstone"), collapsible = TRUE,
             "VISUALISATION LE COVID-19", id="nav",
             
             ####Première tabPanel####
             tabPanel("GLOBAL",
                       sidebarPanel(
                           width= 20,
                           span(tags$i(h1("La revolution globale du Covid-19\n")), style="color:#045a8d"),
                           span(tags$i(h2("Diagramme en barre")), style="color:#045a8d"),
                           selectInput("condition","Choisir observation:",
                                       choices = c("Cas","Décès","Cas par mois","Décès par mois"))
                        ),
                       plotOutput("image",height = 500)
                      ),
             
             ####Deuxième tabPanel####
             tabPanel("Continent",
                  sidebarLayout(
                      sidebarPanel(
                        span(tags$i(h4("Diagramme en barre par continent\n")), style="color:#045a8d"),
                        selectInput("continent","Choisir un continent:",
                                    choices = c("Afrique","Asie","Europe","Amérique du Nord","Océanie","Amérique du Sud")),
                        selectInput("observation","Choisir observation:",
                                    choices = c("Cas","Décès","Cas par mois","Décès par mois"))
                      ),
                      
                      mainPanel(
                        tabsetPanel(
                          tabPanel("Diagramme en barre", plotOutput("barre",height = 750)),
                          tabPanel("Diagramme sectoriel",splitLayout(cellWidths = c("53.5%", "46.5%"), plotOutput("sectoriel_cases",height = 400), plotOutput("sectoriel_deaths",height = 400))),
                          tabPanel("Dendrogramme", plotOutput("continent_dendrogramme",height = 750)),
                          tabPanel("Plan Factoriel",plotOutput("continent_planfactoriel",height = 750))
                        )
                      )
                      
                    )     
             ),
             
             ####Troisième tabPanel####
             tabPanel("Etats-Unis",
                      sidebarLayout(
                        sidebarPanel(
                          span(tags$i(h4("Diagrammes en barre\n")), style="color:#045a8d"),
                          selectInput("US_observation","Choisir observation:",
                                      choices = c("Cas","Décès","Cas par mois","Décès par mois"))
                        ),
                      mainPanel(
                        tabsetPanel(
                          tabPanel("Diagramme en barre", plotOutput("us_barre",height = 750)),
                          tabPanel("Dendrogramme", plotOutput("us_dendrogramme",height = 750)),
                          tabPanel("Plan Factoriel",plotOutput("us_planfactoriel",height = 750))
                                   )
                              ) 
                      )
              ),
             
             ####Quatrième tabPanel####
             tabPanel("Pays",
                        sidebarPanel(
                          width=20,
                          span(tags$i(h4("Diagramme en barre pour observer chaque pays")), style="color:#045a8d"),
                          selectInput("choisir_pays","Choisir Pays: ",choices = country_list$jhu_ID),
                          selectInput("pays_cas_observation","Choisir observation:",
                                      choices = c("Cas","Décès","Cas par mois","Décès par mois"))
                        ),
                      plotOutput("pays_barre",height = 500)
             ),
             
             ####Cinquième tabPanel####
             tabPanel("10K cas et décès",
                      sidebarLayout(
                        sidebarPanel(
                          span(tags$i(h4("Diagrammes en barre\n")), style="color:#045a8d"),
                          selectInput("comparer_observation","Choisir observation:",
                              choices = c("Cas","Décès"))
                        ),
                        mainPanel(
                          tabsetPanel(
                            tabPanel("Diagramme en barre", plotOutput("comparer_barre",height = 750)),
                            tabPanel("Dendrogramme", plotOutput("comparer_dendrogramme",height = 750)),
                            tabPanel("Plan factoriel", plotOutput("comparer_planfactoriel",height = 750))
                          )
                        ) 
                      )
             ),
             
             ####Sixième tabPanel####
             tabPanel("COVID-19 en direct",
                      div(class="outer",
                          leafletOutput("mymap", width="1650", height="940"),
                          
                          absolutePanel(id = "controls", class = "panel panel-default",
                                        top = 80, left = 70, width = 300, fixed=TRUE,
                                        draggable = TRUE, height = 250,
                                        
                                        h3(textOutput("reactive_case_count"), align = "left"),
                                        span(h4(textOutput("reactive_case_count_new"), align = "left"), style="color:#cc4c02"),
                                        span(h4(textOutput("reactive_death_count"), align = "left"),style="color:#636363"),
                                        h6(textOutput("clean_date_reactive"), align = "left"),
                                        h6(textOutput("reactive_country_count"), align = "left"),
                                        tags$i(h6("Mettre à jour dépend les donnés: ", tags$a(href="https://raw.githubusercontent.com/eparker12/nCoV_tracker/master/input_data/coronavirus.csv", "coronavirus.csv"))),
                                        
                                        sliderInput("plot_date",
                                                    label = h5("Selectionner la date de la carte"),
                                                    min = as.Date(cv_min_date,"%Y-%m-%d"),
                                                    max = as.Date(current_date,"%Y-%m-%d"),
                                                    value = as.Date(current_date),
                                                    timeFormat = "%d %b", 
                                                    animate=animationOptions(interval = 2000, loop = FALSE))
                          )
                          
                      )
             ),
             
             ####Septième tabPanel####
             tabPanel("À propos de ce site",
                tags$img(src = "Ecole.png", width = "250px", height = "150px",style="float:left"),tags$img(src = "Departement.png", width = "250px", height = "150px",style="float:right"),
                tags$br(),tags$br(),tags$br(),tags$br(),tags$br(),tags$br(),tags$br(),
                    tags$h2("Professeur: Mme. Marion Kissous & M. Nicolas Sutton-Charani",style="text-align: center;"),tags$br(),
                     tags$div(
                        tags$h4("Objectif"), 
                        "C'est une application qui permet de visualiser en manière très simple pour voir la révolution du COVID-19. Il nous permet de visualiser en globale, par cotinent, par chaque pays qui sont touchés par le virus. En plus, nous pouvons enquêter spécifiquement sur les Etat-Unis pour voir en détail sur les pourcentages de cas ou de décès pour chaque Unis.",tags$br(),tags$br(),
                        "Il nous donne les visualisations par les diagrammes en barres, les diagrammes sectoriels pour voir les nombres ou les pourcentages de cas ou de décès sur quelques critères que j'ai mis en place, les dendrogrammes et les plan factoriels pour voir les classement selon les critères proposés. C'est tous les méthodes qu'on a déjà traité en cours, TPs et les exercices.",tags$br(),tags$br(),
                        "Pour vous assurez, je vous informe que pour visualiser les cas, les décès... sur le tabPanel(Pays), ils ont perdu les informations pour quelques pays quand on a suivi le jeux de donnés (coronavirus.csv) comme: la Chine, Royaume-Uni, les Etats-Unis.. ça veut dire que ils ont pas enregistré pour chaque jour au niveau de la révolution mais plus part de pays on peut les visualiser",
                        tags$br(),tags$br(),
                        "Pour pouvoir visualiser j'ai utilisé quelques jeux de donnés comme: coronavirus_global.csv, coronavirus_continent.csv, coronavirus.csv, coronavirus_today.csv, et coronavirus_state.csv que vous pouvez trouver sur ce lien:",
                        tags$a(href="https://github.com/eparker12/nCoV_tracker/tree/master/input_data"," les jeux de données"),tags$br(),tags$br(),
                        "Plus d'infomations suivi le COVID-19: ",
                        tags$a(href="https://gisanddata.maps.arcgis.com/apps/opsdashboard/index.html#/bda7594740fd40299423467b48e9ecf6", "Johns Hopkins University COVID-19 dashboard"),HTML('&nbsp;'),
                        tags$a(href="https://covid19.who.int/", "WHO Coronavirus Disease (COVID-19) Dashboard"),
                        tags$br(),tags$br(),tags$h4("Conclusion"), 
                        "Je vous remercie pour nous donner le projet application avec shiny en programmation R pour améliorer mes compétences et j'espère que vous préférez cette application. Je crois que à partir de ce projet, j'ai appris beaucoup sur les structures et les components (ui.R et server.R) pour construire les autres applications à l'avenir.",
                        tags$br(),tags$br(),tags$h4("Authors"),
                        "David ING, Etudiant dans le département 2IA à IMT Mines Alès",
                        tags$br(),tags$h4("Contact"),
                        "david.ing@mines-ales.org"
                      )
             )
             
        )
)
