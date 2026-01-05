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

[**Диаграмма контейнеров (Containers)**](https://www.planttext.com?text=fLLTInj157qN_0zJdop4X89FVJ9QA8AsejW-ocHtr6qtks5tKWb5I1Hj8GLBd_fG-la7re_K66Fy2pF_gEVEPfEiMKLgICZUcpldt8zp9tEXv86ibjt9YKUss7GymHPc5tnFSZm6S3gUvLPjmRQbh8HF2uM0x-It7BbTBLL34LY859xCMtwvK76vHqWpeRHTfrHOc9rP8TVwybAXp4Cf0hWs1jZvYc6Td5WHGUXxqmINOrFgX-gfI_r9dUFxH4LwJxN8dhhZDwORggqRnkpg9jFxggkk47EgUaoTuQ6hegd7HBPMGoBbZNc_wjayg4s7JmZtr_2uYcuxpjvFJZ2y1XLCb_sIuufYfGACPVFCOBYEnQNZUvInytAjKFJim7TiCjSN6R1wwbYrJHq3acjT9nVClqZY2kaSKRvfkXrHYhbUYz9DebN1BGcghQhBWyAhgiY_2HF64q0Upr21O8aC-g5k9lWLRhtbMuBDurjuTbBbA36lEC-v53ky1iJYoYBhMsOctp27kZhLTRsF3hOncqWVKW6C7arbFSm70yin51wXIkHW9bOtO-oWvKWgYZ5mgAFQ36-zbrTdUSFnHHyWz8oYW0kQdluGd-i0haqjGt4DEgaFndYJIaTB8hMjYU2TOmdg4YpMDmtX7tC-GdQNyJnKYy7i42bo11kiBkLvdIL9V2woM6r13ClSGyFBs1jGFJCkDlIbylW9fDDOd9HFqjWsQNGyGqmW3kof2wFdapZBh5GyNpgRVG4D6_5Yn9jAv3lG4i9El2CagtWvaWooQ-QI5ptkrgHZXLGmR3Pmf4W-6ndGGCzfxqYgLxg9YUf6Hcq9sUxDFOcxlCHhvaBfDzYOXkMtQW6cI-gWCGAqcICXKQlmYx7EZJKgH6BRfHjaLRZzksfq_TLNQ6sW1NIdfVVeo31wiBrtXOv0ffOE6z2C9SQmVqbq0dcFqD6GjFRZ1TOVxx70MM3PEdeWw4tDF1HkVEt-0t4S9Isqp4k388pEUleeTgdkBLgUm-sBlyH_)

**Диаграмма компонентов (Components)**

[**Диаграмма микросервиса UserService**](https://www.planttext.com?text=XLHDRzD04BrRydyOzK8g9Ugz1zHI9A3Y4DeW3bKFcsJZhkfuezrjgu2GcbH0fLGA1qu82VW7Ja94w4Vw5sR_4RDsseGc3h4KM_QRzzxCpio6DaoPetheU_fGH0scM1qgijwG4O_Chcc671Il61O58VSztti4-1alRHl7zjHs04U0VRp1eJsd_n4EmRPmP5i46EArxVZUtcRn1JnZXf-mvZum3K7wx7jLPbYPQGuhHQbDeFZkgvSh3b4jJvI-uXXlRDkUqlsAhc6gIOBsB7azWjUQgrskZaM5GuuWOnoG_YU2jl0oCKItCLxZhUCYKuwli-fx3LOvP069pp2im3lV0_hjRHwP0og0g30ZP0HRCZ9AXY5N-pC0gSJRhEyxF13QgApFJXHsU4DgOQHgqkltavn_OjzsyJUEV6zIAIWK7cSJmZeyBvMAQohvpjM3iEn4xW936OXeGTJ2xEw2604Wj_scjEfiPvjBs1ucIoIQ7NFtEhVgEYJROrPyJKJLD3uFnro9MhF8j3wHgdhFkDX_5c4eMK9OvAekjAOmdKjpcmCxYcgPOhQVFjboa4WQ3Y6l6P2r_tG6Va-QkUzQcHep1hobXnZxWBzef6Xkx1crQWm9c7eMtAWHqZNkcU_ng0fExbvMYU1WgflTilF2arcXeOZNd6GwPRQRJ-03fvY0O_kH9geBTDY5gHcCbvfPrlxuWmW6wPQOuKyczCfUKEyxdt6oGiZFKftvEIB-BzDAqlIx2iROetn7f1JZzRe37L6WpWFPudHhJ4_zNpaZ36r0_4PrkdG5egtYQj9txcTtppgGwLvob3r2jITdkp2ZYtGrsGykvdPQO_pZUniaJjluBm00)

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
