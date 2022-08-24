
	data model
		[Scheduler] n-1 [RegularJob]
		[RegularJob] n-1 [Form]
		[Form] 1-n [Task]
		[Task] n-1 [Person]
		[Scheduler| scheduler_id; scheduler_title; every_min; at_min; at_hour; at_day_of_month; at_day_of_week; ]
		[Form| form_id; form_title; form_url; ]
		[Person| person_id; person_name; ]
		[RegularJob| regular_job_id; job_title; scheduler_id; form_id; person_id; valid_from; valid_until; ]
		[Task| task_id; task_title; form_id; person_id; created_at; done_at; ]
	Scheduler: Every Friday at 4:00 PM
	Regular Job: Hangi etkinliklere ne kadar vakit harcadınız?
	Form: Yoğunluk_Dağılımı formu
		https://docs.google.com/spreadsheets/d/1Gqqf70oS_i3iRWQYU2SviQAXPAlTUaNkomm5jzbNGek/edit#gid=0
		[Yoğunluk_Dağılımı]
			isim
			proje
			rol
			hafta
			açıklama
			efor
	Task: Yoğunluk_Dağılımı formu doldurma
		Person: Nur Yılmaz
		Time: 20220617 4:00 PM
	uc01: Yeni RegularJob Oluşturma id=g12927
		s01: Planlayıcı, yeni RegularJob oluşturma ekranına girer.
		s02: Planlayıcı, mevcut Scheduler ve Form kayıtlarından seçim yapar
		s03: Planlayıcı, görevin başlığını girer.
		s04: Planlayıcı, görevin atandığı kişiyi seçer.
		s05: Planlayıcı, isterse son geçerlilik tarihini girer.
		s05: Sistem, RegularJob kaydını veritabanına yazar.
		s06: Sistem, Scheduler kuralına uygun bir şekilde önümüzdeki 30 gün için, Task kayıtlarını oluşturur.
		opt: use case'i formal bir şekilde yazalım id=g12928
			a01: EAV formatında yazım
				s01: Sistem, RegularJob formunu sunar.
				s02: Planlayıcı şu EAV'leri girer:
					:RegularJob {
						:scheduler scheduler_name
						:form form_title
						:regular_job/title title
						:regular_job/assignee person_name
						:regular_job/expires_at expiration_date
					}
				s03: Sistem bu entity'yi kaydeder.
				s04: Sistem Scheduler kuralına uygun bir şekilde, 30 günlük Task kayıtlarını oluşturur:
					:Tasks [{
						:task/regular_job job_title
							:task/assignee person_name
							:task/timed_at timestamp
							:task/status :initial
					}]
