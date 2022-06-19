
# Source code from: [Shiny - Persistent data storage in Shiny apps](https://shiny.rstudio.com/articles/persistent-data-storage.html#file)

library(shiny)

# 6. Google Sheets (remote)
# saveData
# loadData

# prerequisite:

# Define the fields we want to save from the form
fields <- c("name", "used_shiny", "r_num_years")

library(googlesheets4)

# SHEET_ID <- "146NShVEQm5fV9bjs0_LGRG-zBAhcFABS"
SHEET_ID <- "https://docs.google.com/spreadsheets/d/146NShVEQm5fV9bjs0_LGRG-zBAhcFABS/edit#gid=695229957"
table <- "responses"

# googlesheets4::gs4_deauth()
# googlesheets4::gs4_auth(email = "mert.nuhoglu@gmail.com")

saveData <- function(data) {
  # The data must be a dataframe rather than a named vector
  data <- data %>% as.list() %>% data.frame()
  # Add the data as a new row
  googlesheets4::sheet_append(SHEET_ID, data)
}

loadData <- function() {
  googlesheets4::read_sheet(SHEET_ID, sheet = table, na = "N/A")
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
