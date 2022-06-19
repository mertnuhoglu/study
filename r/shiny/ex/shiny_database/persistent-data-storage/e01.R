
# Source code from: [Shiny - Persistent data storage in Shiny apps](https://shiny.rstudio.com/articles/persistent-data-storage.html#file)

library(shiny)

# Basic Shiny app without data storage
# formData
# output$responses

# Define the fields we want to save from the form
fields <- c("name", "used_shiny", "r_num_years")

# Save a response
# ---- This is one of the two functions we will change for every storage type ----
saveData <- function(data) {
  data <- as.data.frame(t(data))
  if (exists("data")) {
    responses <<- rbind(responses, data)
  } else {
    responses <<- data
  }
}

# Load all previous responses
# ---- This is one of the two functions we will change for every storage type ----
loadData <- function() {
  if (exists("data")) {
    responses
  }
}

# Shiny app with 3 fields that the user can submit data for
shinyApp(
  ui = fluidPage(
    DT::dataTableOutput("responses", width = 300), tags$hr(),
    textInput("name", "Name", ""),
    checkboxInput("used_shiny", "I've built a Shiny app in R before", FALSE),
    sliderInput("r_num_years", "Number of years using R", 0, 25, 2, ticks = FALSE),
    actionButton("submit", "Submit")
  ),
  server = function(input, output, session) {

    # Whenever a field is filled, aggregate all form data
    formData <- reactive({
      data <- sapply(fields, function(x) input[[x]])
      data
    })

    # When the Submit button is clicked, save the form data
    observeEvent(input$submit, {
      saveData(formData())
    })

    # Show the previous responses
    # (update with current response when Submit is clicked)
    output$responses <- DT::renderDataTable({
      input$submit
      loadData()
    })
  }
)
