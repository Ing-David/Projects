library(shiny)

# Shiny Server
function(input, output) {
  
####PREMIÈRE tabPanel####

#totaux cas confirmés chaque mois   
  nouveaux_cas <- cv_global %>%
    group_by(global_level, Month = format(as.Date(date), "%B")) %>%
    summarise(new_cases_each_month = sum(new_cases, na.rm = TRUE))  
  
#totaux décès chaque mois  
  nouveaux_décès <- cv_global %>%
    group_by(global_level, Month = format(as.Date(date), "%B")) %>%
    summarise(new_deaths_each_month = sum(new_deaths, na.rm = TRUE))
  
  
output$image <- renderPlot({
    
    if(input$condition == "Cas"){
      
      cv_global %>% mutate(date = ymd(date)) %>% 
        arrange(global_level, date) %>%
        group_by(global_level) %>%
        transmute(date, Diff = c(0, diff(cases))) %>% 
        ungroup %>% 
        ggplot(aes(x = date, y = Diff)) +
        labs(title = "Cas chaque jour",x="Mois",y="Cas confirmés")+
        theme(text = element_text(size = 22))+
        geom_col(fill = "yellow") +
        facet_wrap(~ global_level)
    }
    
    else if(input$condition == "Décès"){
      cv_global %>% mutate(date = ymd(date)) %>% 
        arrange(global_level, date) %>%
        group_by(global_level) %>%
        transmute(date, Diff = c(0, diff(deaths))) %>% 
        ungroup %>% 
        ggplot(aes(x = date, y = Diff)) +
        labs(title = "Décès chaque jour",x="Mois",y="Décès")+
        theme(text = element_text(size = 22))+
        geom_col(fill = "red") + 
        facet_wrap(~ global_level)
      
    }
    
    else if(input$condition == "Cas par mois"){
      nouveaux_cas %>%
        mutate(Month = factor(Month, levels = month.name)) %>%
        arrange(Month) %>%
        ggplot(aes(x = Month, y = new_cases_each_month )) +
        labs(title = "Nombre de cas chaque mois",x="Mois",y="Nombre de cas")+
        theme(text = element_text(size = 22))+
        geom_col(fill= "yellow")
    }
    
    else if(input$condition == "Décès par mois"){
      nouveaux_décès %>%
        mutate(Month = factor(Month, levels = month.name)) %>%
        arrange(Month) %>%
        ggplot(aes(x = Month, y = new_deaths_each_month )) +
        labs(title = "Nombre de décès chaque mois",x="Mois",y="Nombre de décès")+
        theme(text = element_text(size = 22))+
        geom_col(fill= "red")
    }
    
  })
  

####DEUXIÈME tabPanel####
  
output$barre <- renderPlot({
  output$barre <- renderPlot({
    
    choisir_continent <- case_when(
      input$continent == "Afrique" ~ "Africa",
      input$continent == "Asie" ~ "Asia",
      input$continent == "Europe" ~ "Europe",
      input$continent == "Amérique du Nord" ~ "North America",
      input$continent == "Océanie" ~ "Oceania",
      input$continent == "Amérique du Sud" ~ "South America"
    )
    
    if (input$observation == "Cas") {
      choisir_condition <- "cases"
      fill_color <- "yellow"
    }
    
    if (input$observation == "Décès") {
      choisir_condition <- "deaths"
      fill_color <- "red"
    }
    
    if (input$observation == "Cas par mois") {
      choisir_condition <- "new_cases"
      fill_color <- "yellow"
    }
    
    if (input$observation == "Décès par mois") {
      choisir_condition <- "new_deaths"
      fill_color <- "red"
    }
    
    
    if((input$observation == "Cas") | (input$observation == "Décès")){
      cv_continent %>% 
        mutate(date = ymd(date)) %>%
        filter(continent_level == choisir_continent) %>%
        arrange(date) %>%
        transmute(date, Diff = c(0, diff(!!sym(choisir_condition)))) %>%
        ggplot(aes(x = date, y = Diff)) +
        labs(title = paste(input$observation, "chaque jour"),x="Mois",y="") +
        theme(text = element_text(size = 20))+
        geom_col(fill = fill_color) 
    }
    
    else if( (input$observation == "Cas par mois") | (input$observation == "Décès par mois") ){
      
      cv_continent %>%
        group_by(Month = format(as.Date(date), "%B")) %>%
        filter(continent_level == choisir_continent) %>%
        summarise(total = sum(!!sym(choisir_condition), na.rm = TRUE))  %>%
        mutate(Month = factor(Month, levels = month.name)) %>%
        arrange(Month) %>%
        ggplot(aes(x = Month, y = total)) +
        labs(title = input$observation, x="Mois",y="") +
        theme(text = element_text(size = 20))+
        geom_col(fill = fill_color)
      
      }
  })

})

output$sectoriel_cases <-renderPlot({
  
  sec_cases <- cv_continent %>%
    mutate(date = as.Date(date)) %>%
    filter(date==max(date)) %>%
    group_by(continent_level) %>%
    summarise(total=sum(cases)) %>%
    mutate(pourcentage= total/sum(total) * 100)
  
  ggplot(sec_cases, aes(x = "",fill= continent_level)) +
    geom_bar(aes(y=pourcentage),width=1,stat="identity")+
    coord_polar(theta = "y")+
    scale_fill_manual(values = c("Lightblue","#AD7366","Lightgreen","Orange","Red","#254290") ,
                      labels = paste0(levels(as.factor(sec_cases$continent_level)), ' (', round(sec_cases$pourcentage,digits = 2), "%)"))+
    labs(x="",y="",title = "\n",fill= "Cas confirmés pour chaque continent")+
    theme(legend.title=element_text(hjust = 0.5,face = "bold"))+
    theme_classic() + 
    theme(legend.title = element_text(color = "red",size = 20),
          legend.text = element_text(size = 15),
          axis.line = element_blank(), 
          axis.text = element_blank(),
          axis.ticks = element_blank(),
          plot.title = element_text(hjust = 4, color = "#666666"))
})

output$sectoriel_deaths <- renderPlot({
  
  sec_deaths <- cv_continent %>%
    mutate(date = as.Date(date)) %>%
    filter(date==max(date)) %>%
    group_by(continent_level) %>%
    summarise(total=sum(deaths)) %>%
    mutate(pourcentage= total/sum(total) * 100)
  
  ggplot(sec_deaths, aes(x = "",fill= continent_level)) +
    geom_bar(aes(y=pourcentage),width=1,stat="identity")+
    coord_polar(theta = "y")+
    scale_fill_manual(values = c("Lightblue","#AD7366","Lightgreen","Orange","Red","#254290") ,
                      labels = paste0(levels(as.factor(sec_deaths$continent_level)), ' (', round(sec_deaths$pourcentage,digits = 2), "%)"))+
    labs(x="",y="",title = "\n",fill= "Décès pour chaque continent")+
    theme(legend.title=element_text(hjust = 0.5,face = "bold"))+
    theme_classic() + 
    theme(legend.title = element_text(color = "red",size = 20),
          legend.text = element_text(size = 15),
          axis.line = element_blank(), 
          axis.text = element_blank(),
          axis.ticks = element_blank(),
          plot.title = element_text(hjust = 4, color = "#666666"))
})


## DATA PROCESSING 
#prenant en compte le nombre total de cas et de décès pour visualiser le dendrogram et le plan factoriel
dataframe <- cv_continent %>%
  mutate(date = ymd(date)) %>%
  filter(date ==last(date)) %>%
  group_by(continent_level) %>%
  summarise(
            total_new_cases=sum(new_cases),
            total_new_deaths=sum(new_deaths))

new_dataframe <- dataframe %>% column_to_rownames('continent_level')


output$continent_dendrogramme <-renderPlot({
  
  res.pca <- PCA(new_dataframe, ncp = 3, graph = FALSE)
  res.hcpc <- HCPC(res.pca, graph = FALSE)

  figure <- fviz_dend(res.hcpc, 
                  cex = 0.9,                     
                  palette = "jco",                
                  rect = TRUE, rect_fill = TRUE, 
                  rect_border = "jco",           
                  labels_track_height = 2,     
                  main = "Dendrogramme prenant en compte le nombre total de cas et de décès",
                  font.main= 23,
  )
  
 return(figure)
})

output$continent_planfactoriel <- renderPlot({
  
  res.pca <- PCA(new_dataframe, ncp = 3, graph = FALSE)
  res.hcpc <- HCPC(res.pca, graph = FALSE)
  
  figure <- fviz_cluster(res.hcpc,
                     repel = TRUE,            
                     show.clust.cent = TRUE,  
                     palette = "jco",         
                     labelsize = 20,
                     main = "Plan factoriel",
                     font.main = 23
  )
  return(figure)
})



####TROISIÈRE tabPanel#### 

output$us_barre <-renderPlot({
  
  if(input$US_observation == "Cas"){
  a <- cv_states %>%
    group_by(state) %>%
    summarise(total= sum(new_cases)) %>%
    mutate(pourcentage= total/sum(total)*100,
           Unis = paste0(state, " (", round(pourcentage, 2), "%)")) 
  
  ggplot(a,aes(x=fct_reorder(state,pourcentage),y=pourcentage,fill = Unis))+
    geom_bar(stat = "identity", position = position_dodge(width=5))+
    labs(title = "Cas en pourcentage pour chaque Unis",x="Unis")+
    theme(text = element_text(size = 15))+
    coord_flip()
  }
  
  else if(input$US_observation == "Décès"){
    b <- cv_states %>%
      group_by(state) %>%
      summarise(total= sum(new_deaths)) %>%
      mutate(pourcentage= total/sum(total)*100,
             Unis = paste0(state, " (", round(pourcentage, 2), "%)")) 
    
    ggplot(b,aes(x=fct_reorder(state,pourcentage),y=pourcentage,fill = Unis))+
      geom_bar(stat = "identity", position = position_dodge(width=5))+
      labs(title = "Décès en pourcentage pour chaque Unis",x="Unis")+
      theme(text = element_text(size = 15))+
      coord_flip()
  }
  
  else if(input$US_observation == "Cas par mois"){
    cv_states %>%
      group_by(Month = format(as.Date(date), "%B")) %>%
      summarise(total = sum(new_cases, na.rm = TRUE))  %>%
      mutate(Month = factor(Month, levels = month.name)) %>%
      arrange(Month) %>%
      ggplot(aes(x = Month, y = total)) +
      labs(title = "Nombre de cas chaque mois", x="Mois",y="Nombre de cas") +
      theme(text = element_text(size = 20))+
      geom_col(fill = "yellow")
    
  }
  
  else if(input$US_observation == "Décès par mois"){
    cv_states %>%
      group_by(Month = format(as.Date(date), "%B")) %>%
      summarise(total = sum(new_deaths, na.rm = TRUE))  %>%
      mutate(Month = factor(Month, levels = month.name)) %>%
      arrange(Month) %>%
      ggplot(aes(x = Month, y = total)) +
      labs(title = "Nombre de décès chaque mois", x="Mois",y="Nombre de décès")+
      theme(text = element_text(size = 20))+
      geom_col(fill = "red")
  }
})

## DATA PROCESSING
## prenant en compte les nombre total de cas et de décès pour chaque Unis 

dataframe_us <- cv_states %>%
  mutate(date = ymd(date)) %>%
  filter(date ==last(date)) %>%
  group_by(state) %>%
  summarise(
    total_new_cases=sum(new_cases),
    total_new_deaths=sum(new_deaths))

new_dataframe_us <- dataframe_us %>% column_to_rownames('state')

output$us_dendrogramme <- renderPlot({
  
  res.pca <- PCA(new_dataframe_us, ncp = 3, graph = FALSE)
  res.hcpc <- HCPC(res.pca, graph = FALSE)
 
  figure <- fviz_dend(res.hcpc, 
                      cex = 0.9,                     
                      palette = "jco",                
                      rect = TRUE, rect_fill = TRUE, 
                      rect_border = "jco",           
                      labels_track_height = 2,     
                      main = "Dendrogramme prenant en compte le nombre total de cas et de décès",
                      font.main= 23,
  )
  return(figure)
})

output$us_planfactoriel <- renderPlot({
  
  res.pca <- PCA(new_dataframe_us, ncp = 3, graph = FALSE)
  res.hcpc <- HCPC(res.pca, graph = FALSE)
  
  figure <- fviz_cluster(res.hcpc,
                         repel = TRUE,            
                         show.clust.cent = TRUE,  
                         palette = "jco",         
                         labelsize = 20,
                         main = "Plan factoriel",
                         font.main = 23
  )
  return(figure)  
})

####QUATRIÈME tabPanel#### 

output$pays_barre <- renderPlot({
     if(input$pays_cas_observation == "Cas"){
       cv %>% mutate(date = ymd(date)) %>% 
         arrange(date) %>%
         filter(country == input$choisir_pays) %>%
         ggplot(aes(x = date, y = new_cases)) +
         labs(title = "Cas chaque jour",x="Mois",y="Cas confirmés")+
         theme(text = element_text(size = 22))+
         geom_col(fill = "yellow") +
         facet_wrap(~ input$choisir_pays)
     }
     
    else if(input$pays_cas_observation == "Décès"){
      cv %>% mutate(date = ymd(date)) %>% 
        arrange(date) %>%
        filter(country == input$choisir_pays) %>%
        ggplot(aes(x = date, y = new_deaths)) +
        labs(title = "Décès chaque jour",x="Mois",y="Décès")+
        theme(text = element_text(size = 22))+
        geom_col(fill = "red") +
        facet_wrap(~ input$choisir_pays)
    }
    
    else if(input$pays_cas_observation == "Cas par mois"){
      cv %>%
        group_by(Month = format(as.Date(date), "%B")) %>%
        filter(country == input$choisir_pays) %>%
        summarise(cases_each_month= sum(new_cases)) %>%
        mutate(Month = factor(Month, levels = month.name)) %>%
        arrange(Month) %>%
        ggplot(aes(x = Month, y = cases_each_month )) +
        labs(title = "Nombre total de cas chaque mois",x="Mois",y="Nombre de cas")+
        theme(text = element_text(size = 15))+
        geom_col(fill= "yellow")
    }
    
   else if(input$pays_cas_observation == "Décès par mois"){
     cv %>%
        group_by(Month = format(as.Date(date), "%B")) %>%
        filter(country == input$choisir_pays) %>%
        summarise(deaths_each_month= sum(new_deaths)) %>%
        mutate(Month = factor(Month, levels = month.name)) %>%
        arrange(Month) %>%
        ggplot(aes(x = Month, y = deaths_each_month )) +
        labs(title = "Nombre total de décès chaque mois",x="Mois",y="Nombre de décès")+
        theme(text = element_text(size = 15))+
        geom_col(fill= "red")
  }
})

####CINQUIÈME tabPanel#### 

output$comparer_barre <-renderPlot({
  
  if(input$comparer_observation == "Cas"){
          condition <- "cases"
          new_data <- assign(paste0("cv_today_",condition,"_10000"),cv_today_cases_10000)      
  }
  
  if(input$comparer_observation == "Décès"){
        condition <- "deaths"
        new_data <- assign(paste0("cv_today_",condition,"_10000"),cv_today_deaths_10000)
  }
  
    new_data %>%
    group_by(country) %>%
    mutate(pays_cases = !!sym(condition), Pays=paste0(country," (",!!sym(condition),")")) %>%
    ggplot(aes(x=fct_reorder(country,!!sym(condition)), y=!!sym(condition), fill = Pays))+
    geom_bar(stat = "identity", position = position_dodge(width=5))+
    labs(title = paste0("Les pays ont plus de 10K ",tolower(input$comparer_observation)))+
    xlab("Pays")+ylab(input$comparer_observation)+
    theme(text = element_text(size = 15))+  
    coord_flip()
})

## DATA PROCESSING
#prenant en compte le nombre de cas et de décès plus de 10K

cas_décès_10000 <- cv_cas_décès_10000 %>%
  group_by(country) %>%
  summarise(total_cas_par_pays= cases,
            total_décès_par_pays= deaths)

new_cas_décès_10000 <- cas_décès_10000 %>% column_to_rownames("country")


output$comparer_dendrogramme <- renderPlot({
  
  res.pca <- PCA(new_cas_décès_10000, ncp = 3, graph = FALSE)
  res.hcpc <- HCPC(res.pca, graph = FALSE)
  
  figure <- fviz_dend(res.hcpc, 
                      cex = 0.9,                     
                      palette = "jco",                
                      rect = TRUE, rect_fill = TRUE, 
                      rect_border = "jco",           
                      labels_track_height = 2,     
                      main = "Dendrogramme prenant en compte le nombre total de cas et de décès plus de 10K",
                      font.main= 23,
  )
  return(figure)
})

output$comparer_planfactoriel <- renderPlot({
  
  res.pca <- PCA(new_cas_décès_10000, ncp = 3, graph = FALSE)
  res.hcpc <- HCPC(res.pca, graph = FALSE)
  
  figure <- fviz_cluster(res.hcpc,
                         repel = TRUE,            
                         show.clust.cent = TRUE,  
                         palette = "jco",         
                         labelsize = 20,
                         main = "Plan factoriel prenant en compte le nombre total de cas et de décès plus de 10K",
                         font.main = 23
  )
  return(figure)  
})


####SIXIÈME tabPanel#### 

output$mymap <- renderLeaflet({
  leaflet(plot_map) %>% 
    addTiles() %>% 
    addProviderTiles(providers$CartoDB.Positron) %>%
    fitBounds(~-100,-50,~80,80) %>%
    clearShapes() %>%
    addPolygons(data = reactive_polygons(), stroke = FALSE, smoothFactor = 0.1, fillOpacity = 0.15, fillColor = ~cv_pal(reactive_db_large()$newper100k)) %>%
    
    addCircles(data = reactive_db(), lat = ~ latitude, lng = ~ longitude, weight = 1, radius = ~(cases)^(1/5)*2.3e4*penalty, 
             fillOpacity = 0.1, color = "#cc4c02", group = "2019-COVID (cumulative)",
             label = sprintf("<strong>%s (cumulative)</strong><br/>Cas confirmés: %g<br/>Décès: %d", reactive_db()$country, reactive_db()$cases, reactive_db()$deaths) %>% lapply(htmltools::HTML),
             labelOptions = labelOptions(
               style = list("font-weight" = "normal", padding = "3px 8px", "color" = "#cc4c02"),
               textsize = "15px", direction = "auto"))
})


reactive_db = reactive({
  cv %>% filter(date == input$plot_date)
})

reactive_db_large = reactive({
  large_countries = reactive_db() %>% filter(alpha3 %in% worldcountry$id)
})

reactive_polygons = reactive({
  worldcountry[worldcountry$id %in% reactive_db_large()$alpha3, ]
})

output$reactive_case_count <- renderText({
  paste0(prettyNum(sum(reactive_db()$cases), big.mark=","), " cas")
})

output$reactive_death_count <- renderText({
  paste0(prettyNum(sum(reactive_db()$death), big.mark=","), " décès")
})

output$reactive_case_count_new <- renderText({
  paste0(prettyNum((cv_aggregated %>% filter(date == input$plot_date & region=="Global"))$new, big.mark=",")," nouveaux cas")
})

output$reactive_country_count <- renderText({
  paste0(nrow(subset(reactive_db(), country!="Diamond Princess Cruise Ship")), " pays/régions touchées")
})

output$clean_date_reactive <- renderText({
  format(as.POSIXct(input$plot_date),"%d %B %Y")
})

}

