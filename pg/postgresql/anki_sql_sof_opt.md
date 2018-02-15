
## Best way to check for “empty or null value”

i use:

coalesce( trim(stringexpression),'')='' <br>

stringexpression = '' yields:

{{c1::TRUE}}   .. for ''  <br>
{{c2::NULL}}   .. for NULL <br>
{{c3::FALSE}} .. for anything else <br>

stringexpression is either NULL or empty:

(stringexpression = '') IS {{c4::NOT FALSE}} <br>
(stringexpression &lt;&gt; '') IS {{c5::NOT TRUE}} <br>

test

··  SELECT stringexpression  <br>
····  ,stringexpression = ''··················  AS simple-test <br>
····  ,(stringexpression = '')  IS NOT FALSE··  AS test1 <br>
····  ,(stringexpression &lt;&gt; '') IS NOT TRUE··   AS test2 <br>
····  ,coalesce(stringexpression, '') = ''····  AS test-coalesce1 <br>
····  ,coalesce(stringexpression, '  ') = '  '  AS test-coalesce2 <br>
····  ,coalesce(stringexpression, '') = '  '··  AS test-coalesce3 <br>
··  FROM  ( <br>
····  {{c1::VALUES}} <br>
····  ('foo'::char(5)) <br>
····  , ('') <br>
····  , (NULL) <br>
····  , ('   ')  <br>
··  ) sub(stringexpression); <br>

%

%

clozeq

---

