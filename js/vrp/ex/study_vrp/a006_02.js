const {makeHTTPDriver} = require('@cycle/http')
const xs = require('xstream').default

const HTTP = makeHTTPDriver()
const requests$ = xs.from(
  [
    {
      url: 'http://localhost:8300/post_run',
      method: 'POST',
			headers: { 'Content-Type': 'application/json' },
			send: '{"sevk_emri_data": [["irsaliye_no", "irsaliye_sevk_tarihi", "cari_kod", "unvan", "teslIm_adres", "teslim_sehir", "teslim_sehir__1", "malzeme_kodu", "malzeme_adi", "irsaliye_KG", "tesis", "IL", "ILCE", "Yaş/Toz"], ["1043", "2017-01-02T00:00:00Z", "2I00048", "ISIKSAN YALITIM INS. TAAH. SAN. VE TIC. LTD. STI.", "FABRIKADAN TESLIM ALACAGIZ.", "TEKİRDAĞ", "TÜRKİYE", "1032248PX20", "PE 5088 RAL3004 Smooth", "20", "ÇERKEZKÖY", "TEKIRDAG", "CERKEZKOY", "Toz"], ["1044", "2017-01-02T00:00:00Z", "1P00009", "BSH EV ALETLERI SANAYI VE TICARET A.S.", "YILDIRIM BEYAZID MAH.HAYRI BATUR CAD. NO: 72 CERKEZKOY", "TEKİRDAĞ", "TÜRKİYE", "1021378EB500", "G Miles+ W87 Vzf 07020 Smooth", "1000", "ÇERKEZKÖY", "TEKIRDAG", "CERKEZKOY", "Toz"], ["1045", "2017-01-02T00:00:00Z", "1P00009", "BSH EV ALETLERI SANAYI VE TICARET A.S.", "YILDIRIM BEYAZID MAH.HAYRI BATUR CAD. NO: 72 CERKEZKOY", "TEKİRDAĞ", "TÜRKİYE", "1023813EB300", "G Miles+ W88 Vzf 07020 Smooth", "300", "ÇERKEZKÖY", "TEKIRDAG", "CERKEZKOY", "Toz"]], "planningDate": "2017-01-03T00:00:00", "planId": "single_20170103a6"}',
			category: 'express'
    },
  ]
)
const httpSource = HTTP(requests$)

const plans$ = httpSource.select('express')
  .flatten()
  .map(res => 
    res.body
  )
plans$.addListener({
  next: i => console.log(`result: ${JSON.stringify(i)}`),
  error: err => console.error(err),
  complete: () => console.log('s1 completed'),
})



