  <url:file:///~/Dropbox (Personal)/projects/study/study/study_js.md>

# Study JS

    conventions
    nodejs
      string templates
        backtick ` not "
        `//${ocpu_domain}/ocpu/library/stats/R`
      import source require
        local library
          var $ = require("jquery")
        global library
          npm link <library>
          var $ = require("jquery")
      passing server side parameters to client side js
        https://stackoverflow.com/questions/5927824/best-practice-for-passing-server-side-variables-to-javascript
          2 correct ways
            data as a service: REST service for parameter
            inject data into HTML: js will extract it from HTML
        expressjs
          https://stackoverflow.com/questions/10919650/accessing-express-js-local-variables-in-client-side-javascript
            sending data to pug template
              nodejs
                var myMongo = {name: 'stephen'}
                res.render('home', {locals: {data: myMongo}})
              home.pug
                p Hello #{data.name}
              output in html
                Hello stephen
            sending data to client side js
              opt1: data in HTML
                home.pug
                  - local_data = JSON.stringfiy(data)
                  input(type='hidden', value=local_data)#myLocalDataObj
                client side js
                  var localObj = JSON.parse($("#myLocalDataObj").val())
                  console.log(localObj.name)
              opt2: data as rendered js
                script.
                  var local_data = !{JSON.stringify(data)}
              opt2b: 
                nodejs
                  res.render('search-directory', {
                    title: 'My Title',
                    place_urls: JSON.stringify(placeUrls),
                  });
                client js
                  var placeUrls = !{place_urls};
          https://stackoverflow.com/questions/16098397/pass-variables-to-javascript-in-expressjs
            nodejs
              res.render(.., { arg: "arg data" } )
            html
              <script> var arg = .. </script>
            normal data aktarımı 
              h1= title
              p Welcome to #{title}
      dotenv: environment variables configuration
        https://github.com/motdotla/dotenv
          const dotenv = require('dotenv');
          dotenv.load({ path: '.env.example' });
        define in .env
          MONGODB_URI=mongodb://myUserAdmin:12345@localhost:27017/test
        use
          mongoose.connect(process.env.MONGODB_URI);
        define in docker-compose.yml
          environment:
           - MONGODB_URI=mongodb://myUserAdmin:12345@mongo:27017/beyp_poc
    process
      get working directory getwd pwd
        process.cwd()
    file system io
      ls list files
        ex
          var dir = './'; // your directory
          var files = fs.readdirSync(dir);
        sort files
          files.sort(function(a, b) {
            return fs.statSync(dir + a).mtime.getTime() - 
            fs.statSync(dir + b).mtime.getTime();
          });
    expressjs
      error: csrf token missing
        # req.path içine POST form'un adresini ekle
          app.use((req, res, next) => {
          if (req.path === '/api/upload' || req.path === '/uploadData') {
            next();
          } else {
            lusca.csrf()(req, res, next);
          }
          });
      using html inside pug
        opt1: just put | pipe symbol in front as if it is raw text
          .content
            | <div>Hello, yes this is dog</div>
        opt2: just put html tags
          <div>Hello, yes this is dog</div>
      using html file
        res.sendFile
          exports.getTest2 = (req, res) => {
            const path = __dirname;
            res.sendFile(path + "/test2.html");
        dynamic routing for static files
          opt1:
            app.get('/test/:uid', function(req, res){
              var uid = req.params.uid;
              var path = "test/" + uid + ".html";
              res.sendfile(path, {root: './public'});
            });
            # put html files into: public/test/ folder
            # use http://x.com/test/file
          opt2: 
            app.use('/static', express.static('public'))
            # put html files into: public/ folder
            # use http://x.com/static/file.html
      change start port
        opt1
          PORT=3001 npm start
          PORT=3001 nodemon app.js
        opt2: gulp
          gulpfile.js
            nodemon({
              script: 'bin/www',
          bin/www
            app.set('port', process.env.PORT || 3003);
        opt3: environment variable
          PORT=3001
        opt4: intellij
          environment variable
            PORT=3001
      run nodemon from intellij
        configuration
          node parameters: /Users/mertnuhoglu/.nvm/versions/node/v7.7.4/bin/nodemon
          javascript file: app.js
    pug jade id=g_10139
      pug jade <url:file:///~/Dropbox/mynotes/content/articles/articles_js.md#r=g_10139>
      https://webapplog.com/jade/
        tags: first word
          body
            div content
            div
            | this is content too
        variables
          pug
            h1= title
            p= body
          locals
            {
              title: "guide"
              body: ..
        attributes
          div(id="content")
          div(id=variable)
          a#main-link
            <a id="main-link"></a>
        use | for text as well
        literals: classes and IDs right after tag names
          div#content
          p.center
          #notag
        if no tag used, then it is div
        script/style blocks
          script.
            console.log(..)
          style.
            css code
        execute js code
          - var arr = [3,2]
          span= arr[1]
          span!= "unescaped"
        comments
          // comment
          //- comment hidden from html
        conditions if
          if user.admin
            ..
          else
            ..
        iterations each loops
          div
            - var languages = {'php': 2}
            each key, value in languages
              p= key + ". " + value
            - var items = {'php', 'java'}
            each item, index in items
              td= item
        filters: for markdown
          p
            :markdown
              # practical
        interpolation via #{name}
          p read #{title}
        case
          case coins
            when 0
              p ..
            when 1
              p ..
        mixins: functions that produce html. usage is +fun(params)
          mixin row(items)
            tr 
              each item, index in items
                td= item
          mixin table(tableData)
            table
              each row, index in tableData
                +row(row)
          - var node = [{name: "express"}]
          +table(node)
        include
          include ./inc/header
          include ../inc/footer
        extend: replace parent files
          parent
            block header
              p ...
          child
            extend parent
            block header
              p overwrite it
        standalone usage
          tldr
    twitter bootstrap id=g_10145
      twitter bootstrap <url:file:///~/Dropbox/projects/study/study/study_js.md#r=g_10145>
      cheatsheets
        https://hackerthemes.com/bootstrap-cheatsheet/
      themes
        dashgum: saf html
        blur: çok güzel, fakat angular
        AdminLTE
        coreui: güzel
        gentetella: çok kapsamlı
      template builders
        free
          http://www.layoutit.com/build
          http://angrytools.com/bootstrap/editor/
          https://pingendo.com/new
        http://bootstrapstarterkit.com
        https://mobirise.com
        brix.io
        http://www.cssauthor.com/bootstrap-editors/
        http://mashable.com/2013/10/20/bootstrap-editors/#2EoYSV03PaqW
      intellij support
        bootstrap templates
          bs3-...
          shortcut: #j
      bootstrap setup
        opt1: bower
          http://stackoverflow.com/questions/36160883/getting-start-with-node-express-and-bootstrap
            sudo npm install bower -g
            nano .bowerrc
            {
            "directory":"./public"
            }
            bower install bootstrap --save
        opt2: manual
      tabs in bootstrap
        http://getbootstrap.com/javascript/#tabs
          opt1: only markup
            <ul class="nav nav-tabs" role="tablist">
              <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">Home</a></li>
            <div class="tab-content">
              <div role="tabpanel" class="tab-pane active" id="home">tab1</div>
    handsontable
      ref
        /Users/mertnuhoglu/projects/itr/vrp/frontend/views/test_pug/handsontable01.pug
      documentation pro 
        https://docs.handsontable.com/pro/1.11.0/tutorial-introduction.html
        @note
          kopyala yapıştır kendinden destekliyor
          yeni satırları otomatik oluşturuyor
          json da olabilir data, array de
      ex: basic
        html
          div(id="spreadsheet")
        js
          var data = [
              ["", "Ford", "Volvo", "Toyota", "Honda"],
              ["2016", 10, 11, 12, 13],
              ["2017", 20, 11, 14, 13],
              ["2018", 30, 15, 12, 13]
          ];
          /* our DIV container */
          var container = document.getElementById('spreadsheet');
          /* spreadsheet initialization */
          var hot = new Handsontable(container, {
              data: data,
              rowHeaders: true,
              colHeaders: true,
              dropdownMenu: true
          });
      ex: createSpreadsheetData()
        var data = function () {
            return Handsontable.helper.createSpreadsheetData(100, 10);
        };
        var hot = new Handsontable(container, {
            data: data(),
            ...
      ex: data as array of objects
        var dataObject = [
            {
                id: 1, flag: 'EUR', currencyCode: 'EUR', },
      ex: columns settings
        columns: [
            {
                data: 'id',
                type: 'numeric',
                width: 40
            },
      ex: hot.getData()
        hot.getData();
        // hot.getData().slice(0,2)
        // (2) [Array(7), Array(7)]0: Array(7)0: ""1: "Ford"2: "Nissan"3: "Toyota"4: "Honda"5: "Mazda"6: "Ford"length: 7__proto__: Array(0)1: Array(7)length: 2__proto__: Array(0)
      ex: ocpu custom function
        ocpu.seturl("//localhost/ocpu/user/opencpu/library/pmf/R")
      ex: ocpu upload_data
        var arg1 = hot.getData();
        // (2) [Array(7), Array(7)]0: Array(7)0: ""1: "Ford"2: "Nissan"3: "Toyota"4: "Honda"5: "Mazda"6: "Ford"length: 7__proto__: Array(0)1: Array(7)length: 2__proto__: Array(0)
        var req1 = ocpu.call("upload_data", {mat: arg1}, function (session) {
            mysession = session;
            mysession.getConsole(function (outtxt) {
                $("#output1").text(outtxt);
            });
        }).fail(function () {
            alert("Error: " + req.responseText);
        });
        // > upload_data(mat = mat)
        //     V1  V2     V3     V4    V5    V6   V7
        // 1 Year Kia Nissan Toyota Honda Mazda Ford
        // 2 2012  10     11     12    13    15   16
        // 3 2013  10     11     12    13    15   16
      ex: ocpu load_data from R to hot
        var req1 = ocpu.call("get_data", {}, function (session) {
            mysession = session;
            mysession.getObject(function (data) {
                var
                    container1 = document.getElementById('example2'),
                    settings1 = { data: data };
                var hot2 = new Handsontable(container1, settings1);
                hot2.render();
      ex: date
        function getCarData() {
            return [
                ["Ford", "A 160", new Date(), 6999.9999],
                ["Ford2", "A 160", new Date().toISOString(), 6999.9999],
                ["Ford3", "A 160", new Date().toDateString(), 6999.9999],
                ["Ford4", "A 160", new Date().toLocaleDateString(), 6999.9999],
                ["Mercedes", "A 160", "01/14/2012", 6999.9999],
        columns: [
          {
              type: 'date',
              dateFormat: 'MM/DD/YYYY',
              correctFormat: true,
              defaultDate: '01/01/1900'
          },
    hackathon-starter
      mongodb credentials
        .env.example
          #MONGODB_URI=mongodb://localhost:27017/test
          MONGODB_URI=mongodb://myUserAdmin:12345@localhost:27017/test
    date
      utc gmt 
        utc: coordinated universal time
          this is a standard not a time zone
          but people use it as time zone of GMT as well
        gmt: greenwich mean time
          this is the time zone
      Date.toISOString()
        https://www.ecma-international.org/ecma-262/6.0/#sec-date.prototype.toisostring
        time zone is always UTC, denoted by suffix Z
        ex: 2013-02-04T22:44:30.652Z
      Date Time String Format: Simplification of ISO 8601 Extended Format
        https://www.ecma-international.org/ecma-262/6.0/#sec-date-time-string-format
        YYYY-MM-DDTHH:mm:ss.sssZ
          T: separator
          HH: 00-24
          sss: milliseconds
          Z: time zone
            Z: UTC
            + or -
      Date.toLocaleDateString
      string to date
        opt1: Date.parse()
          Date.parse(dateString)
          dateString in ISO8601 format
          Date.parse("12.04.2017")
          Date.parse("13.04.2017")
            NaN
          Date.parse("12.30.2017")
          new Date("12.30.2017")
          new Date("13.04.2017")
            Invalid
        opt2: moment.js
      new Date
        var today = new Date();
        var birthday = new Date('December 17, 1995 03:24:00');
        var birthday = new Date('1995-12-17T03:24:00');
        var birthday = new Date(1995, 11, 17);
        var birthday = new Date(1995, 11, 17, 3, 24, 0);
      moment
        install
          npm install -g moment
          var moment = require()
        basic
          var now = moment();
        string to date
          moment: string to date
            moment(String, String);
            var dt = moment("1995-12-25", "YYYY-MM-DD");
            # ignores separators:
            moment("12-25-1995", "MM-DD-YYYY");
            moment("12/25/1995", "MM-DD-YYYY");
          moment: multiple options
            moment("12-25-1995", ["MM-DD-YYYY", "YYYY-MM-DD"]);
          unix timestamp 
            (milliseconds)
              var day = moment(1318781876406);
            seconds
              var day = moment.unix(1318781876);
        date to string: format()
          opt1: moment(date).format(str)
            StartDate = moment(StartDate).format('YYYY-MM-DD');
          opt2: moment(new Date(str))
            var dateString = 'Thu Jul 15 2016 19:31:44 GMT+0200 (CEST)';
            var dateObj = new Date(dateString);
            var momentObj = moment(dateObj);
            var momentString = momentObj.format('YYYY-MM-DD'); // 2016-07-15
            moment("25/01/1995", "DD/MM/YYYY").toISOString() // 2013-02-04T22:44:30.652Z
            moment("2017/07/14 18:19", "YYYY/MM/DD HH:mm").toISOString()
            # "2017-07-14T15:19:00.000Z"
            moment("2017/07/14 18:19", "YYYY/MM/DD HH:mm").format() // 2017-07-14T18:19:00+03:00
            moment("2017/07/14 18:19", "YYYY/MM/DD HH:mm").format("YYYY-MM-DDTHH:mm:ss") // 2017-07-14T18:19:00
      iso8601 date formats
        Date: 2017-07-04
        Combined date and time in UTC:  
          2017-07-04T11:24:41+00:00
          2017-07-04T11:24:41Z
          20170704T112441Z
        Week: 2017-W27
        Date with week number:  2017-W27-2
        Date without year:  --07-04
        Ordinal date: 2017-185
      iso8601
        new Date("2017-07-04")
        new Date(Date.parse("2017-07-04"))
        moment("2017-07-04")
    chrome debug tools
      chrome'da yaptığın değişikliklerin dosya sistemine kaydedilmesi 
        https://developers.google.com/web/tools/setup/setup-workflow
        debug > source > right > Add Folder to Workspace
        .select a file > Map to File System Resource
    widgets
      jquery widgets
        datettimepicker
          http://xdsoft.net/jqplugins/datetimepicker/
          how to use
            page scripts
              <link rel="stylesheet" type="text/css" href="/jquery.datetimepicker.css"/ >
              <script src="/jquery.js"></script>
              <script src="/build/jquery.datetimepicker.full.min.js"></script>
            use
              HTML
                <input id="datetimepicker" type="text" >
              javaScript
                jQuery('#datetimepicker').datetimepicker();

