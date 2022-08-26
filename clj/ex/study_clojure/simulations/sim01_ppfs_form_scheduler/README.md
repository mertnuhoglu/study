
# Simulation 01: Form Scheduler id=g13189

Ref:

[sim_regular_tasks.xlsx - Google Sheets](https://docs.google.com/spreadsheets/d/10lRHF-qgi5ScyGjGc-9nsBlg76F4YvjM/edit#gid=474433315)

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

### Model Short id=g13195

             /-> p1
             |
      fd1 -> rj3 -> sch2
      | |
      | \-> [dd1 + dd2]
      v
      [fn11 + fn12 +⋯+ fn17]
       |      |
       v      |data
       p1     \---> {dd2: "ali"
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

