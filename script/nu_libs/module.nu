# ~/prj/study/script/nu_modules/module.nu
export-env {
    $env.NU_LIB_DIRS = ($env.NU_LIB_DIRS | append [
        ([$env.PWD, "modules"] | path join)
    ])
}
