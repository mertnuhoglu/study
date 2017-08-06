  <url:file:///~/projects/study/study_py.md>

# Study Python

  jupyter notebook ipython
    official documentation 
      https://jupyter.readthedocs.io/en/latest/running.html
      running
        jupyter notebook
          starts notebook server
          open http://localhost:8888
      notebook dashboard
        start notebook server in the highest level directory containing notebooks
    shortcuts
      command mode
        h   show keyboard shortcuts
        m   to markdown
        y   to code
      edit mode
        ^enter  run cell
        +enter  run and select below
    Jupyter Notebook Tutorial - Introduction, Setup, and Walkthrough-HW29067qVWk.mp4
      new notebook
        dashboard > new > python 3
        # kernel: R, python3, python2 are different kernels
      help > user interface tour
      help > keyboard shortcuts
      cells
        print("hello")
        "hello"
        > Out[1]
      run
        run all cells
        run all above
      shell command
        !pip list
        %: magic commands
        %lsmagic
          list all magic commands
        %pwd
        %ls
        %ls -la
        %matplotlib inline
          allows plot in notebook
      showing plots
        ^enter doesn't render plot outputs
        1. %matplotlib inline 
          (above)
        2. ^enter
      embed html
        %%HTML
        <iframe ...>
      run js
        %%javascript
      time commands
        %%timeit
        # some py command
      dataframe render
        import pandas as pd
        import numpy as np
        df = pd.DataFrame(np.random.randn(10,5))
        df.head()
      export notebooks
        as html
          file > download as > html
      share notebook
        .ipynb is json file
      sources
        gallery of interesting ipython notebooks
      import notebooks
        download > copy to notebook server's root directory > jupyter > go to home
    Jupyter Notebooks in the cloud (Brett Cannon)-cVwDGEhk6ck.mp4
      ex
        %% bash
          curl ...
      jupyter cells are not for py only if they contain magic
      third paryt magic
        sql
          %load_ext sql
          %sql sqlite://
            # connected
          %%sql
            DROP TABLE ...
          %sql SELECT * FROM hockey ...
      note: 
        %sql inline sql
        %%sql multiline sql
      render customized way
        Thing._repr_html_()
      capture output of a cell magic and assign it to a variable
        data = %sql SELECT * FROM ...
        data.pie()
      interactive js ui
        pivottable.js
      diagrams with Graphviz
        %load_ext hierarchymagic
        %%dot
        digraph G { 
          rankdir = LR;
          hello -> world;
          goodby -> cruel -> world;
        }
      interactive forms
        from ipywidgets import interact
        /Users/mertnuhoglu/Dropbox/public/img/ss-206.png
      writing custom extensions
        

