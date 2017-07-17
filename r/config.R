
save_config = function(
    max_bicak = 6,
    capacity_km = 215,
    bobin_genislikleri = c(1750,1850,1950,2100,2300,2450),
    day_start_char = "2016-01-08",
    trim_margin_mm = 30,
    yuzde_kazanc = 100,
    mm2_to_m2 = 10^6,
    mm_to_km = 1000000,
    date_format = "%Y%m%d",
    simulation_length_in_days = 365,
    optimize_trim_length_in_days = 365,
    ek_kapasite_katsayisi = 2.5
  ){
  default = list(
    max_bicak = max_bicak,
    capacity_km = capacity_km,
    bobin_genislikleri = bobin_genislikleri,
    day_start_char = day_start_char,
    trim_margin_mm = trim_margin_mm,
    yuzde_kazanc = yuzde_kazanc,
    mm2_to_m2 = mm2_to_m2,
    mm_to_km = mm_to_km,
    date_format = date_format,
    simulation_length_in_days = simulation_length_in_days,
    optimize_trim_length_in_days = optimize_trim_length_in_days ,
    ek_kapasite_katsayisi = ek_kapasite_katsayisi
  )
  yml = list( default = default )
  writeLines(as.yaml(yml), "../../../../config.yml")
}
