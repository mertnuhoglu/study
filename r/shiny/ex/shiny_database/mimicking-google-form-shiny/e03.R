
# Source code from: [Mimicking a Google Form with a Shiny app](https://deanattali.com/2015/06/14/mimicking-google-form-shiny/)

# Save the response upon submission
# fieldsAll
# responsesDir
# epochTime
# formData
# saveData
# humanTime

library(shiny)
library(shinyjs)

fieldsMandatory <- c("name", "favourite_pkg")

labelMandatory <- function(label) {
  tagList(
    label,
    span("*", class = "mandatory_star")
  )
}

appCSS <- ".mandatory_star { color: red; }"

fieldsAll <- c("name", "favourite_pkg", "used_shiny", "r_num_years", "os_type")
responsesDir <- file.path("data")
epochTime <- function() {
  as.integer(Sys.time())
}

shinyApp(
  ui = fluidPage(
    shinyjs::useShinyjs(),
    shinyjs::inlineCSS(appCSS),
    titlePanel("Mimicking a Google Form with a Shiny app"),
    div(
      id = "form",

      textInput("name", labelMandatory("Name"), ""),
      textInput("favourite_pkg", labelMandatory("Favourite R package")),
      checkboxInput("used_shiny", "I've built a Shiny app in R before", FALSE),
      sliderInput("r_num_years", "Number of years using R", 0, 25, 2, ticks = FALSE),
      selectInput("os_type", "Operating system used most frequently",
                  c("",  "Windows", "Mac", "Linux")),
      actionButton("submit", "Submit", class = "btn-primary")
    )
  ),
  server = function(input, output, session) {
    formData <- reactive({
      data <- sapply(fieldsAll, function(x) input[[x]])
      data <- c(data, timestamp = epochTime())
      data <- t(data)
      data
    })

    humanTime <- function() format(Sys.time(), "%Y%m%d-%H%M%OS")

    saveData <- function(data) {
      fileName <- sprintf("%s_%s.csv",
                          humanTime(),
                          digest::digest(data))

      write.csv(x = data, file = file.path(responsesDir, fileName),
                row.names = FALSE, quote = TRUE)
    }

    # action to take when submit button is pressed
    observeEvent(input$submit, {
      saveData(formData())
    })

    observe({
      # check if all mandatory fields have a value
      mandatoryFilled <-
        vapply(fieldsMandatory,
               function(x) {
                 !is.null(input[[x]]) && input[[x]] != ""
               },
               logical(1))
      mandatoryFilled <- all(mandatoryFilled)

      # enable/disable the submit button
      shinyjs::toggleState(id = "submit", condition = mandatoryFilled)
    })
  }
)
