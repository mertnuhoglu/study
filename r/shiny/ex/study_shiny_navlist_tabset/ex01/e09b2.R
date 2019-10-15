library(shiny)
myTabs = lapply(paste('Tab', 1: 3), tabPanel)
myTabs
  ##> [[1]]
  ##> <div class="tab-pane" title="Tab 1" data-value="Tab 1"></div>
  ##> 
  ##> [[2]]
  ##> <div class="tab-pane" title="Tab 2" data-value="Tab 2"></div>
  ##> 
  ##> [[3]]
  ##> <div class="tab-pane" title="Tab 3" data-value="Tab 3"></div>
t2 = tabPanel("Master")
t2
  ##> <div class="tab-pane" title="Master" data-value="Master"></div>
t3 = list(t2)
  ##> [[1]]
  ##> <div class="tab-pane" title="Master" data-value="Master"></div>

