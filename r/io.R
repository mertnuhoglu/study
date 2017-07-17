
r_Data = function(data = "verify", file_name = "test_data", ext = "xlsx") {
	import( get_data(data, file_name, ext) )
}

w_Data = function(df, data = "verify", file_name = "test_data", ext = "xlsx") {
	export(df, get_data(data, file_name, ext) )
}

r_Siparis_v1 = function(file_name = "test_data", ext = "xlsx") {
	import(get_siparis_v1(file_name, ext)) 
}

r_Siparis_v2 = function(file_name = "test_data", ext = "xlsx") {
	import(get_siparis_v2(file_name, ext)) %>%
    mutate( ie_tarihi = as.Date(ie_tarihi, format = g_cfg$date_format) ) %>%
    mutate( termin_tarihi = as.Date(termin_tarihi, format = g_cfg$date_format) )
}

rename_columns_Siparis_v1 = function(sip) {
  sip %>%
    rename( 
      tesis = `İşYeri`,
      id = `İE Lot No`,
      stok_kodu = `Stok Kodu`,
      kalite_kodu = `Kalite Kodu`,
      siparis = `Sipariş`,
      str = `Str`,
      en = `L.Eni`,
      boy = `L.Boy`,
      ie_tarihi = `İE Tarihi`,
      termin_tarihi = `Terrmin Tarihi`,
      siparis_adet = `Miktar`
    ) 
}

r_Kombin_raporu_java = function(file_name = "test_data", ext = "xlsx") {
	import(get_kombin_v1(file_name, ext)) %>%
    mutate( ie_tarihi_1 = as.Date(as.character(ie_tarihi_1), format = g_cfg$date_format) ) %>%
    mutate( ie_tarihi_2 = as.Date(as.character(ie_tarihi_2), format = g_cfg$date_format) ) %>%
    mutate( termin_tarihi_1 = as.Date(as.character(termin_tarihi_1), format = g_cfg$date_format) ) %>%
    mutate( termin_tarihi_2 = as.Date(as.character(termin_tarihi_2), format = g_cfg$date_format) ) %>%
    mutate( kesim_tarihi = as.Date(as.character(kesim_tarihi), format = g_cfg$date_format) ) %>%
    mutate( kombin_termin = as.Date(as.character(kombin_termin), format = g_cfg$date_format) )
}

r_Kombin_v2 = function(file_name = "test_data", ext = "xlsx") {
	import(get_kombin_v2(file_name, ext)) 
}

r_Kombin_raporu = function(file_name = "test_data", ext = "xlsx") {
	import(path_kombin_raporu(file_name, ext)) 
}

r_Siparis = function(file_name = "test_data", ext = "xlsx") {
	import(get_siparis(file_name, ext)) %>%
    mutate( ie_tarihi = as.Date(ie_tarihi, format = g_cfg$date_format) ) %>%
    mutate( termin_tarihi = as.Date(termin_tarihi, format = g_cfg$date_format) )
}

r_Kombin = function(file_name = "test_data", ext = "xlsx") {
	import(get_kombin_xlsx(file_name, ext)) 
}

r_IsEmri = function(file_name = "test_data", ext = "xlsx") {
	import(get_is_emri_xlsx(file_name, ext)) 
}

r_KapasiteKullanim = function(file_name = "test_data", ext = "xlsx") {
	import(get_kapasite_kullanim(file_name, ext)) 
}

w_Siparis_v1 = function(df, file_name = "test_data", ext = "xlsx") {
	export(df, get_siparis_v1(file_name, ext)) 
}

w_Siparis_v2 = function(df, file_name = "test_data", ext = "xlsx") {
	export(df, get_siparis_v2(file_name, ext)) 
}

w_Kombin_raporu_java = function(df, file_name = "test_data", ext = "xlsx") {
	export(df, get_kombin_v1(file_name, ext)) 
}

w_Kombin_v2 = function(df, file_name = "test_data", ext = "xlsx") {
	export(df, get_kombin_v2(file_name, ext)) 
}

w_Kombin_raporu = function(df, file_name = "test_data", ext = "xlsx") {
	export(df, path_kombin_raporu(file_name, ext)) 
}

w_Siparis = function(df, file_name = "test_data", ext = "xlsx") {
	export(df, get_siparis(file_name, ext))
}

w_Kombin = function(df, file_name = "test_data", ext = "xlsx") {
	export(df, get_kombin_xlsx(file_name, ext)) 
}

w_IsEmri = function(df, file_name = "test_data", ext = "xlsx") {
	export(df, get_is_emri_xlsx(file_name, ext)) 
}

w_KapasiteKullanim = function(df, file_name = "test_data", ext = "xlsx") {
	export(df, get_kapasite_kullanim(file_name, ext)) 
}

