
# Simulation 01: Form Scheduler id=g13189

Ref:

[sim_ppfs_form_scheduler.xlsx - Google Sheets](https://docs.google.com/spreadsheets/d/10lRHF-qgi5ScyGjGc-9nsBlg76F4YvjM/edit#gid=474433315)

## Model id=g13198

example:

    FormDef:
      fd1: her pazartesi 15:00 - p1 p2 p3
      fd2: her gün 18:00 - p2 p4
    Person: p1 p2 p3
    FormIns:
      fn11: fd1 - date - p1 - m11
    DatafieldDef:
      dd1: 
        title: Project
        type: ProjectEnum
      dd2:
    FieldInsMap:

yuml:

  [FormDef]
  [Person]
  [FormIns]
	[DatafieldDef]

			evler, restoranlar  bankalar fabrikalar gibi
			evler, restoranlar  bankalar fabrikalar gibi
### Model Short id=g13195

             ┌-> p1
             |
      fd1 -> rj3 -> sch2
      | |
      | └-> [dd1 + dd2]
      ↓
      [fn11 + fn12 +⋯+ fn17]
       |      |
			 ↓      |data
       p1     └-> {dd2: "ali"
                     dd1: dd31 }

## Inbox List id=g13223

```{r}
get_inbox :: person date -> [formN]
get_inbox p d =
	[fn] |>
		filter(person = p) |>
		filter(done = F) |>
		filter(job-date <= d)
```

## EDN Data id=g13224

rfr: `~/projects/study/clj/ex/study_clojure/simulations/sim01_ppfs_form_scheduler/sim01_data.edn`

## Date Regex Matching id=g13229

rfr: `Scheduling regex matches <url:file:///~/projects/myrepo/projects.otl#r=g13227>`

Farklı tarih regexleri nasıl olur:

- her haftanın 3. günü -> Δ1w + 3d
- her ayın 5. günü -> Δ1mo + 5d
- her gün -> Δ1d 
- her 3 saatte bir -> Δ3h
- her 2 haftada bir -> Δ2w
- her 3 günde bir -> Δ3d
- her 3 iş gününde bir -> Δ3D

Her ayın 5. günü:

```
y = x₀ + m·x
x₀: 5d
m: Δ1mo
```

- her ayın 3. cuması -> 

```
haftalık formülü çek	
	x₀: 2d = 3 - 1
	m: Δ1w
ilk 4 hafta için bu formülü uygula	
	ilk haftanın başlangıcı: 2022-08-29
	2022-08-29 + 2d
	2022-08-29 + 2d + 7d
	...
	2022-08-29 + 2d + 28d
kontroller:	
	bu tarih bugünden büyük mü?
	bu tarih iptal (son) tarihten küçük mü?
	bu tarih geçici (son) tarihten küçük mü?
```


