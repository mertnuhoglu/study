library(shiny)
runApp(list(
  ui=fluidPage(
    fluidRow(
      actionLink("newTab", "Append tab"),
      actionLink("removeTab", "Remove current tab")
    ),
    tabsetPanel(id="myTabs", type="pills")
  ),
  server=function(input, output, session){
    tabIndex <- reactiveVal(0)
    observeEvent(input$newTab, {
      tabIndex(tabIndex() + 1)
      appendTab("myTabs", tabPanel(tabIndex(), tags$p(paste("I'm tab", tabIndex()))), select=TRUE)
    })
    observeEvent(input$removeTab, {
      removeTab("myTabs", target=input$myTabs)
    })
  }
))

