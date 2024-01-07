
# datomic-pro maven setup id=g13697
  id:: a4f895dd-855f-4a6e-9865-9ca827a40f8a

rfr: grsm/video/20230207-mert-clj-egzersiz-24.mp4

https://my.datomic.com/account adresinde erişim konfigürasyon ayarlarını bulabilirsiniz. Herkesin ayrı hesabı olduğundan, herkese ayrı şifre sağlanır. 

In ~/.m2/settings.xml:

		<!-- ~/.m2/settings.xml (see the Maven server settings docs) -->
			<servers>
				…
				<server>
					<id>my.datomic.com</id>
					<username>mert.nuhoglu+2@gmail.com</username>
					<password>...</password>
				</server>
				…
			</servers>

Örnek settings.xml: grsm/inbox/maven-settings.xml

In deps.edn:

		{:mvn/repos
		{"my.datomic.com" {:url "https://my.datomic.com/repo"}}
		:deps
		{com.datomic/datomic-pro {:mvn/version "${VERSION}"}}} 

Örnek deps.edn: /Users/mertnuhoglu/prj/study/clj/ex/study_datomic/datalog-03/deps.edn
