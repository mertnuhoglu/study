  <url:file:///~/projects/study/study_py.md>

# Study Python

  jupyter notebook ipython
    graphviz
    install single file
      wget -P "`python -m site --user-site`" https://raw.github.com/cjdrake/ipython-magic/master/gvmagic.py
      python -m site --user-site
        /Users/mertnuhoglu/.local/lib/python3.5/site-packages
      mkdir -p "`python -m site --user-site`"
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
    Teaching with the IPython Notebook-j91xUVgbQ9M.webm
      ex: doc hints
        BlockGrid?
      ex: grid.<tab>
      ex: %nose
        tests passed and failed
    IPython Notebook best practices for data science-JI1HWUAyJHE.mp4
      slideshow in jupyter
      @SVDataScience
      ex: embed iframe
        IFrame("http://...")
      ex: data csv
        dframe = pd.read_csv("...csv")
        dframe.head()
      ex: _7
        Out[7]
        _i7
        In[7]
      extensions
        qqrid
          datatable widget
        sql
          dbtest = %sql SELECT ...
          dbtest[['col1']].head()
      organization
        one notebook for lab 
        one notebook for documenting how data is cleaned
        notebooks are shared in git
      interior organization
        toc
          analysis in this notebook
          tips
          extensions
            %matplotlib inline
            %load_ext version_information
            %version_information numpy, scipy, matplotlib, pandas
          imports
            import numpy as np
            ...
          customizations
            plt.rcParams ...
            fig_prefix = "../figures/"
          importing cleaned data
            from IPython.display import FileLink
            FileLink("../clean.ipynb")
      naming conventions
        [ISO8601_date]-[DS-initials]-[description]
        2015-07-13-mn-coal_productivity.ipynb
      directories
        data/
          outside of version control
        deliver/
          final polished notebooks
        develop/
          lab notebooks
        figures/
        src
          scripts/modules
      tips
        commit: ipynb, html, py, figures
          all notebooks (incl lab)
        use post-save hooks
          convert notebooks to .py and .html
          bit.ly/post-save-hook-snippet
        record of analysis including dead-ends
        peer review analysis 
        sometimes wrong thing solves a problem
          storing output figures, .html
      tools
        jupyter slides
          https://github.com/damianavila/RISE
        nbdiff.org
    Andrew Campbell - Bootstrapping Applications and Dashboards with IPython Widgets-B_qWHQHey9M.mp4
      parse.ly/code
      quantopian
        crowd sourced hedge fund
        algorithmic trading
        take cut of profit
      zipline
        algorithmic backtest tool
      pyfolio
        algorithmic trading tool
      jupyterhub
        multiuser server
      ipywidgets
        full app with widgets
        forms
          dropdowns, text fields etc
        buttons 
          on_click function
        layout: WBox, HBox
        qgrid
          datatable widget
        pass row from view (js) to model (py)
          %%javascript code
      ex: github ipywidgets_app_boots
    JupyterHub - Deploying Jupyter Notebooks for students and researchers-gSVvxOchT8Y.mp4
      notebook is a webapp
      how
        authentication
        single-user servers on demand
    Peter Parente, Gino Bustelo, Justin Tyberg _ Turning Jupyter Notebooks into Data Applications-V3VxQGevHCU.mp4
      use case:
        i have a notebook
        i need to convert it to a dashboard as web app
      declarativewidgets
      bind html widgets to functions
        code
          def est_table()...
          %%html
            <template
              <function ref="est_table"...>
              <table datarows="est_table.data">
        html markup is declaratively written
      views:
        usual notebook code view
        dashboard view
          show/hide cells
          edit layout of cells
      webcomponents
        to create completely new html elements(tags)
          ex: google-map
            <template ...>
              <google-map ...
        polymer: to make webcomponents easy
        data binding: between html components and notebook kernel
    Randall J. LeVeque - Writing a Book in Jupyter Notebooks-mC_RERKi56c.mp4
      make interactive mathematical functions
      ex:
        from ipywidgets import interact
        interact(...)
        # creates an interactive widget
      ex: animation
        from utils.jsanimate_widgets import interact
      ex: 
    Tetiana Ivanova - How to become a Data Scientist in 6 months a hacker’s approach to career planning-rIofV14c0tc.mp4
    The IPython Notebook Revolution-t_TzRaK9kpU.webm
      ex: function documentation
      ex: doc about magic functions
        %xmode?
      ex: bash commands
        pwd
        cd
        rm
        ls
      ex: run python
        run x.py
        %debug
        %load file.py
      ex:
        Javascript("...d3.js")
        Javascript("
          container.show()
            ")
        HTML("...")
      ex: use pasted code
        >>>
        ....
      ex: load from url
        %load http://...file.py
      ex: give a presentation
      ex: interesting ipython notebooks
    Volodymyr (Vlad) Kazantsev - Clean Code in Jupyter notebooks, using Python-2QLgf2YLlus.mp4
      tip: write tests
        pytest-ipynb
      tip: to cloud
      tip: run notebook from another nb
        run_notebook('x.ipynb', param1)
        # parameters
          x.ipynb:
            params = {}
            params['param1'] = ...
      tip: make data product
        UIBuilder()
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
        ex
          %hierarchy get_ipython()
      interactive forms
        from ipywidgets import interact
        /Users/mertnuhoglu/Dropbox/public/img/ss-206.png
      writing custom extensions
        
    Brian Granger - All About Jupyter-GMKZD1Ohlzk.mp4
      humans: consume stories.
        combine stories with data
      hint: links to other notebooks
      ex: narrative
        headline: Most Police Dont Live in the Cities they Serve
        code:
          from IPython.display import Image
          Image('img.png', width = 600)
        without story data doesn't make sense
      kernels: python, R, bash
      tab completion work
      ex: image
        cat img.png | display
        # display is a bash function
      Notebook Ecosystem
        from IPython.display import display, Image, HTML
        nbconvert
          cli tool
          to convert to html, md, pdf, slideshow
        nbviewer
          publish nb files on web
        binder: live notebooks on github
        jupyter tmpnb
          run nb in cloud temporarily
        jupyterhub
          multiuser server
      What People Do?
        OReilly Thebe: run some functions on nb cloud
        notebook gallery: awesome list
          interesting nb
          reproducible research
      in Journalism
        buzzfeed
          for every data driven article has own github repo and jupyter notebook
        LA Times
      in commercial Products
        Quantopian
          algorithmic trading platform 
          on top of jupyter
    Gerrit Gruben - Leveling up your Jupyter notebook skills-b8g-8T0amuk.mp4


