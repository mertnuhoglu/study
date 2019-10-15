library(shiny)
t0 = do.call(tabsetPanel, list(tabPanel("Master")))
t0
  ##> <div class="tabbable">
  ##>   <ul class="nav nav-tabs" data-tabsetid="9523">
  ##>     <li class="active">
  ##>       <a href="#tab-9523-1" data-toggle="tab" data-value="Master">Master</a>
  ##>     </li>
  ##>   </ul>
  ##>   <div class="tab-content" data-tabsetid="9523">
  ##>     <div class="tab-pane active" data-value="Master" id="tab-9523-1"></div>
  ##>   </div>
  ##> </div>
t1 = tabsetPanel(list(tabPanel("Master")))
t1
  ##> <div class="tabbable">
  ##>   <ul class="nav nav-tabs" data-tabsetid="5072">
  ##>     <li class="active">
  ##>       <a href="#tab-5072-1" data-toggle="tab"></a>
  ##>     </li>
  ##>   </ul>
  ##>   <div class="tab-content" data-tabsetid="5072">
  ##>     <div class="tab-pane" title="Master" data-value="Master"></div>
  ##>     tab-pane active
  ##>     tab-5072-1
  ##>   </div>
  ##> </div>
t2 = tabsetPanel("Header", list(tabPanel("Master")))
  ##> Error in divTag$attribs : $ operator is invalid for atomic vectors
t3 = tabsetPanel(tabPanel("Master"))
t3
  ##> <div class="tabbable">
  ##>   <ul class="nav nav-tabs" data-tabsetid="7863">
  ##>     <li class="active">
  ##>       <a href="#tab-7863-1" data-toggle="tab" data-value="Master">Master</a>
  ##>     </li>
  ##>   </ul>
  ##>   <div class="tab-content" data-tabsetid="7863">
  ##>     <div class="tab-pane active" data-value="Master" id="tab-7863-1"></div>
  ##>   </div>
  ##> </div>
t4 = tabsetPanel(id = "Header", tabPanel("Master"))
t4

