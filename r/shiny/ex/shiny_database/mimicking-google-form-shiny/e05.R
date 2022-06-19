
# Source code from: [Mimicking a Google Form with a Shiny app](https://deanattali.com/2015/06/14/mimicking-google-form-shiny/)

# Add table that shows all previous responses
# responsesTable
# loadData

library(shiny)
library(shinyjs)

fieldsMandatory <- c("name", "favourite_pkg")

labelMandatory <- function(label) {
  tagList(
    label,
    span("*", class = "mandatory_star")
  )
}

appCSS <-
  ".mandatory_star { color: red; }
   #error { color: red; }"


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
    DT::dataTableOutput("responsesTable"),
    div(
      id = "form",

      textInput("name", labelMandatory("Name"), ""),
      textInput("favourite_pkg", labelMandatory("Favourite R package")),
      checkboxInput("used_shiny", "I've built a Shiny app in R before", FALSE),
      sliderInput("r_num_years", "Number of years using R", 0, 25, 2, ticks = FALSE),
      selectInput("os_type", "Operating system used most frequently",
                  c("",  "Windows", "Mac", "Linux")),
      actionButton("submit", "Submit", class = "btn-primary")
    ),

    shinyjs::hidden(
      span(id = "submit_msg", "Submitting..."),
      div(id = "error",
          div(br(), tags$b("Error: "), span(id = "error_msg"))
      )
    ),

    shinyjs::hidden(
      div(
        id = "thankyou_msg",
        h3("Thanks, your response was submitted successfully!"),
        actionLink("submit_another", "Submit another response")
      )
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

    loadData <- function() {
      files <- list.files(file.path(responsesDir), full.names = TRUE)
      data <- lapply(files, read.csv, stringsAsFactors = FALSE)
      data <- do.call(rbind, data)
      data
    }
    output$responsesTable <- DT::renderDataTable(
      loadData(),
      rownames = FALSE,
      options = list(searching = FALSE, lengthChange = FALSE)
    )

    saveData <- function(data) {
      fileName <- sprintf("%s_%s.csv",
                          humanTime(),
                          digest::digest(data))

      write.csv(x = data, file = file.path(responsesDir, fileName),
                row.names = FALSE, quote = TRUE)
    }

    # action to take when submit button is pressed
    observeEvent(input$submit, {
      shinyjs::disable("submit")
      shinyjs::show("submit_msg")
      shinyjs::hide("error")

      tryCatch({
        saveData(formData())
        shinyjs::reset("form")
        shinyjs::hide("form")
        shinyjs::show("thankyou_msg")
      },
        error = function(err) {
          shinyjs::html("error_msg", err$message)
          shinyjs::show(id = "error", anim = TRUE, animType = "fade")
        },
        finally = {
          shinyjs::enable("submit")
          shinyjs::hide("submit_msg")
        })
    })


    observeEvent(input$submit_another, {
      shinyjs::show("form")
      shinyjs::hide("thankyou_msg")
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
