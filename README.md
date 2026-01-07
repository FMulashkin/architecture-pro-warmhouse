# Project_template

Это шаблон для решения проектной работы. Структура этого файла повторяет структуру заданий. Заполняйте его по мере работы над решением.

# Задание 1. Анализ и планирование

<aside>

Чтобы составить документ с описанием текущей архитектуры приложения, можно часть информации взять из описания компании и условия задания. Это нормально.

</aside

### 1. Описание функциональности монолитного приложения

**Управление отоплением:**

- Пользователи могут удаленно регулировать отопление (вкл/выкл через приложение)
- Система поддерживает регулирование температуры, подключение новых сенсоров, удаление существующих сенсоров

**Мониторинг температуры:**

- Пользователи могут удаленная проверка температуры 
- Система поддерживает проверку значений сенсоров, подключение новых сенсоров, удаление существующих сенсоров

### 2. Анализ архитектуры монолитного приложения
- БД: PostgreSQL
- ЯП: Go
- Взаимодействие: синхронное через HTTP
- Структура: монолит
- Регистрация: закрытая

### 3. Определение доменов и границы контекстов
As-Is:
Домен управления устройствами:
	Поддомен добавления/удаления устройств
	Поддомен изменения температуры
Домен мониторинга:
	Поддомен отслеживания температуры

To-Be:
Домен управления устройствами:
	Поддомен добавления и удаления устройств
	Поддомен изменения состояния:
		Контекст: регуляция температуры
		Контекст: управление воротами
Домен мониторинга:
	Поддомен мониторинга устройств:
		Контекст: отслеживание температуры
		Контекст: отслеживание состояния ворот
		Контекст: видеонаблюдение
	Поддомен отчетности и аналитики
Домен подключения:
	Поддомен регистрации и авторизации клиентов

### **4. Проблемы монолитного решения**

- Если количество пользователей сильно увеличиться, то монолитную систему будет очень сложно масштабировать
- Сложность добавления интеграции с новыми устройствами
- Сложность параллельной разработки и тестирования при росте системы
- Если же функционал системы планирует оставаться ограниченным, а сам продукт нишевым, то сильно усложнять разработку и переходить на микросервисы нет смысла

### 5. Визуализация контекста системы — диаграмма С4

[C4 монолита](https://www.planttext.com?text=fLJVQzDG57uFv7-uziKAha7Oaq_3AhvCg0wVHzfSjqAQbEG6DqHOMyI73eOo44JcmnY-TdDnjMtQV-7U_yZlt3HDjsP3zAL9x_tE-NwSGpP2OGSYQhccSS_XRveUPzNr6jiLeXq-jgp0VblPQOhTg1w5F6Zudk2Ug3JybjLsRO_graBXkCswLLrVgz7HwnURLiiE1G_GiUartCZXKU1ILs2sgsXXWoMej5FIOWZV4tE0QTHu4FfUcGGyP2NvNKxbM1tAApmlv41rPKp_ImzCOsiVlArog1-4_GPCGdWv18xUOYPdwa0ESJILlr2Qu3tEYxUVxObooBtGrsJ7HA0-0ZDYccaYPp960phkgGFLfzA5gQVribD7NSqFnKx0jrvkqhLft6VoAqdHBKQgexeBlYT-v3bsi5zkF2B26p2cEf3UIQc9ZBqp3SR-FXB6_YkKhFoVOdc_OlqtEiTqpQ0p0R9Bj3TCBkLvcsg6rm5xxfTma_xA4tbE_F9SJj5eX6ljvYTf98gUjZW0xpWrg8wOXauXVPnvbXC6C4ueq1uSBg2Ng4sWkQy-eAxGTHhPoZB8JoYz8a5BFKhNjuHWPrfVaacv3i_Mw3DrmlK42YyfKPtR1MaqZLVSdUz4djjfxbiTGMtt3jS0qWBHX59euMnx98QeCf9yBxw0I7U1glujcy4Afq5-2tGjgEOKkQyJRHiYYQYp51OOXtTFQ5bxEg4V6gmvBHtmJ2_auSeCDhZduBFq1m00)

# Задание 2. Проектирование микросервисной архитектуры

В этом задании вам нужно предоставить только диаграммы в модели C4. Мы не просим вас отдельно описывать получившиеся микросервисы и то, как вы определили взаимодействия между компонентами To-Be системы. Если вы правильно подготовите диаграммы C4, они и так это покажут.

[**Диаграмма контейнеров (Containers)**](https://www.planttext.com?text=ZLLRInj157uN_0zJdop4B0MV-cHKA8AsejW-ocGp9blt4dOdbL24HD4MKh3qgG-brp-mNb9ZZF4lpFoZVcSs6sEo5YMoUoRdVD-vV6Uo44aUofhdJayzAOaTnnTiQNufy2N7QuX3nxVTMacmYfJLwBbbXNmlNtPafLQiHIAquIbycRS3pwgwt2UakKYMNATeBStFhTFHrjggvV58YX17sqFiVDMmJa-jYp0A_5c2ox4PzKFrrRN-f2xnF5EnFb1jicV-yntJJTNHJMFsT8lfGzLJD_0vLtscBl3IK_7CKoBRh2CHRtingFab7jPdesU4--ikNCKFXRFtqrCCVyCAPhsWwBYYKAq2OysyCnYkOtFf13vbxBpQj0f-AGoS4fbRAmnOVNMgEgQE8ScjRj0HpBz8uWRfd52-z-dsH37XUYEAuqGRWjiILEMQoqFhTKtatqOfunaWJ-UeGB341lrOjrByAhTtULcmHJo5Nqgh72NcLUS5bsAFru5OM5zX0ylCv1lcq53dkg4FqS4EPXFhOog0qQkfh8_vO60vXi9ZL8aSpCGQPenTj1n9nGa6Whggm_3H1tbraJSSN_GHN2_82xYWwUiFILmNS1rg6OfhKgG-ck1DAnqjYTGMKN_eQJZ-c90O2Lqd8r1j1hDBF4WB1B1wbDfjbWhnVvL5L1Bl75kiSHyzzh0ge5esH-pkpF3_1C9veaFA8-rXX-GucHcQdJZsrPMHxbcINLOAVY2TdO5MgEQNexRX_Ww0bAERR0299f5-IffT7fUyu7EtBXsRi9TXi-610VziXajZkgHj8W7Uw1Rcf9iPPQGa--FgJngvoklccXZqq9Y6vRTg0wP7CwT90RIL8tbGL_2DiIwDDIelOjkdUs53k8CRQ7IfrLSeQ3XkTAMj3oZapdkua_zp7O6yjqhGTIjH4SDMfTufv2DSHrrIHLkXA3jqs-QOvGPbnyUJrsGbAsPS6TReGLR0-BYJxTSV7x7zcN2Jos79YWYz03zBqXHWx-9XSq-4F4KOope311Va9YA5J4CcVUxBANFxAM-Zfhu-IOvKxm75Bk3cnC_PFm00)

**Диаграмма компонентов (Components)**

[**Диаграмма микросервиса Users**]([https://www.planttext.com?text=ZLFDJXin4BwljE_WvXGao5uuSGA2r1Q159AW7geAERlJZOLZh-pPehIgL2gLKn-X3n6G43ILu1MyRzHn8B14tgfxyNg-dnb_ihSjSeFbHCRHgmm-2WMikzdL2Zdz6YeAbSeo0pP6BEnMaXX-ria5ZijHQS6an0I5dLHFaa9ovPqsB6PIZ9BkvaRFbuuF3v89jmY6IYTFtfrYqTMU2bLmmoUCF0gjo6s0KmdCG8fSvHBYA8wULEsS8vpnwJfhxVJUijSFkzPQZR8tQcSS-OXR85PFMymD38uEF2cE2fwUyXnOwvZ67u3v959eiIznnEXxtuTSM3GSXLRUq6WfmNnuH7TA76iZFly3fl55se3NpHyx1vq3sBFj2kXFqOT2Mu7QJAdwrH-o3_8vgF2PA9etms4lCGiKZ4_dXQpXi4kTrBbGGL4e0bAnXOmJWRNttmtNbjgcyG9faSjIrJXTACaLNNZ0OCmrPN2Ub_4tpVT_z43pHlugKBlm-tkxtOUhZWBf7RbVxjRDgs_khZftrxHUkTlgdDMk_nPpT-wIa9cx9DPtDtSpvkwf-AVwwMvelN8pAb_xFPdCoU27rzml_6RL1NNu7KVRe39wqty1](https://www.planttext.com?text=bPJFQjj04CRl0ht3rYS76kkIKqv9vT8_fE3AzgcKi9QcyfBrhjWTDRYbq0JQKn-X3v44GbmNt5TOlL572abiTnNeITgP-NxppOZLWKLki9p9C7YImGUXWCLxiLR8wTLGKAXKbXcmAM9XzwF8yDDUBd1QJaeB9gLAKDXBzImg95SrQTTY9iKaYlTs1tLe_EOecd6BO2XqVC_k5KrNUo9KmGsVCM8KMX5jY7C9p42ANEKImY0CxbNTd2ESylbJrZaSl68lRa-TdRMI_gIRSUGJRe6g1jfYRc3uzgWk2eE2foSy1zOPattROP_3WB5t2UJ2ek4ejAf1Hai9vdsJEonngetur9eaooBrPjUnGp0VHGeUvZ-P1-16gdQSGA6jG6tc5FjI3vI0V5YAVmvQmylHQ12P9Wk6TPFdmz5ElP0DTSkaTtAfSw4Uq_eMGMBMg3aLR8dRt7hMHfWxLQjBtrAtT7w7tfslARr-Dhy6LSV9kC_SolrnrzLNTr5zTmkty9fkPoJ0iqSP7fl_1zckNhigh_lFujjBWWBf1hgV95mIObMTDQWhjwZEcdvslsvmMUFT9UNFtT9TCEguSh-h7-w6dbVK-TnTrsUIBqdwRTlKhp0u09Kr_u6_))

[**Диаграмма микросервиса DeviceManagement**](https://www.planttext.com?text=ZLJBRjD05DrRyZyCMHM9nfkkkYeu4W-bKaZJ5KBenhuuLiPZQpoXYX0I1S6cgwxOy14M8i8yslx2-8-uuz3u4KTbDtFcd3FdtklnKQf0gaN4RUkUZwz3WSmzS6EXW9QIm51uVE4ZcocLf8UE8-6i7uHgjfWkKfGUCL6elXT7Ji916AVzLFayd3hkmVx8GAV7GoU2LA4awDN6kv-KjwRpK2GW8MBaaSI2t4xKaYEJw2aG0KVRigsDQYy0XMUmVC1w3qTFsUFrhdU_HXbCztnGC8KKYJMAKnL8F7a-xD5EN-fC_z0PqzzfSQMlYelYar7RLWBU709a0tmJUdWCWZQHIOFI0EojRJ7sOZ936P4bA7EVZ3b7-R8y6SRUl8sv4A64Dbfv_FEk31h0Mjs0AkaOapWDLIoNbRgDhGqQw3jJwXXvrSUkYaodlb23Reht-heuBoxOaybavAWDrRIiuTAiVeUU4sbBsUxH3grNqcxLtQ6t6ajUjufsm5gtQwpjC8qXr9WTTtKDXnJk-7J0zBM-qIigAIi-wrpdzLWRW-OCxvAsahM7VPUyEstjvJmRF7BhCV-3jfsXWsXRAbJqmFLNYlDhFUKou3UT5-UxtjyXApwMtqH6lDzwHOytroi2wHFvO4pqJxEYmqp_qRbj7Q7mwG_p5m00)

[**Диаграмма микросервиса Notifications**](https://www.planttext.com?text=ZLFDRXCn47w2VGStfrHgivUUUYfi43zgKSYcue0GykvEjrPsxPNjK4K8YO2AX3WWxbnuWmWH4NxIleBzHemt8d6YhShD_cQ-R_oDPuwKfbAFoYBOoU2CSI3HGIIufdYKmGxZQJ7AW9nhNQd3C9JqefCpVJvAHWfaYed0TISLPLWLb3kTVQMpWYLXTB3VSz3foN5OKgL18lHY9TsfN4qrPBoYafO49Ih1KIpMumA8X5HJdXSG12jAEwSQBkXuZxJkz1wI-yjRQzVBoE0bI-64Sff3YMAOsgqXiiPQ29glPcRVc6zcRYV4lZFNU9dYzRUPcGM2dn2q4_iMuIlpqvqmE3L_pDmlrqtQ6TKqeGfGioUKpYN4Juxh2fyn_uUP4lCT3mkpi1_j9PA3YgP3V0bvB3GxOodLJ715NWM4FFCXLqIAeW3vV3iKWtIMQdp0If0-48jq2Df7V6eVAg6O5dAyPllOwo3eGx5kTFEBqDw3mQ0NSYzAslrxyM0N7Jg1ctWDDb2iWRGADhyUIL7_j4lmTwxhdvpQzsPU_ywDIXkTMLVrExX7jZfoIvrDxd-Ry_Mz0NhKlHlvmhUdRLbgcFwda2oJtEXffd6rp1TqyCjDjfruMx0nZuSDIs5copPS8JIp7_vjYbkAu0XuvlRvBm00)

[**Диаграмма микросервиса DataAnalytics**](https://www.planttext.com?text=TPBDIiD0482VODzXxQc2DXTFdjHK_46XjlKa8jDaZ8lRJTYTAaK45VJYoRiFKKInAAsli7aZ9nNYNtlRdPdlcybELXo1fLvNYxaOZvL16Iw5gI7WenLpoaIw5wCy8ShSSX1OEAybYavwdPv36t4X6gf5QJV8D9Z2i-WergeJX4kBZIAqlxiJTC4HMWuTbUfQLlHqfyfaOA4hMP6bXcKjwckK5YC2asWKeaIg2H2UGtz1LbORMtBZwrQP_wweTwen47J08HSrKaU9nTRUJeLl_i4F_AiVIF_CXv4VvVVvBSCYW-WK4fHr9bSDw3wfoCaB8UN1hr3HnQPQeptybskXFLCH_ayqCKkTejJs3yMb44tKtrynmywpRhRRZG3AMQhDzLPxdcSjy9dKj87ODGqhipDcPInixjUb7_iF_v9V-K5-vuT-ECtra_hn-DlrjR1uNLB4w_IFZB-nQ9nVJuHFVfXVJzhBqhGi9uiP-7TEtXJ0dss95JGn_pQV)

**Диаграмма кода (Code)**

Добавьте одну диаграмму или несколько.

# Задание 3. Разработка ER-диаграммы

Добавьте сюда ER-диаграмму. Она должна отражать ключевые сущности системы, их атрибуты и тип связей между ними.

# Задание 4. Создание и документирование API

### 1. Тип API

Укажите, какой тип API вы будете использовать для взаимодействия микросервисов. Объясните своё решение.

### 2. Документация API

Здесь приложите ссылки на документацию API для микросервисов, которые вы спроектировали в первой части проектной работы. Для документирования используйте Swagger/OpenAPI или AsyncAPI.

# Задание 5. Работа с docker и docker-compose

Перейдите в apps.

Там находится приложение-монолит для работы с датчиками температуры. В README.md описано как запустить решение.

Вам нужно:

1) сделать простое приложение temperature-api на любом удобном для вас языке программирования, которое при запросе /temperature?location= будет отдавать рандомное значение температуры.

Locations - название комнаты, sensorId - идентификатор названия комнаты

```
	// If no location is provided, use a default based on sensor ID
	if location == "" {
		switch sensorID {
		case "1":
			location = "Living Room"
		case "2":
			location = "Bedroom"
		case "3":
			location = "Kitchen"
		default:
			location = "Unknown"
		}
	}

	// If no sensor ID is provided, generate one based on location
	if sensorID == "" {
		switch location {
		case "Living Room":
			sensorID = "1"
		case "Bedroom":
			sensorID = "2"
		case "Kitchen":
			sensorID = "3"
		default:
			sensorID = "0"
		}
	}
```

2) Приложение следует упаковать в Docker и добавить в docker-compose. Порт по умолчанию должен быть 8081

3) Кроме того для smart_home приложения требуется база данных - добавьте в docker-compose файл настройки для запуска postgres с указанием скрипта инициализации ./smart_home/init.sql

Для проверки можно использовать Postman коллекцию smarthome-api.postman_collection.json и вызвать:

- Create Sensor
- Get All Sensors

Должно при каждом вызове отображаться разное значение температуры

Ревьюер будет проверять точно так же.


# **Задание 6. Разработка MVP**

Необходимо создать новые микросервисы и обеспечить их интеграции с существующим монолитом для плавного перехода к микросервисной архитектуре. 

### **Что нужно сделать**

1. Создайте новые микросервисы для управления телеметрией и устройствами (с простейшей логикой), которые будут интегрированы с существующим монолитным приложением. Каждый микросервис на своем ООП языке.
2. Обеспечьте взаимодействие между микросервисами и монолитом (при желании с помощью брокера сообщений), чтобы постепенно перенести функциональность из монолита в микросервисы. 

В результате у вас должны быть созданы Dockerfiles и docker-compose для запуска микросервисов. 
