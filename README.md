# kodland
 
Сервис реализации бизнес-процесса HR-департамента Kodland (интерфейс консольный)

Cодержит следующие функции:
- CRUD стержневых сущностей (create, read, update, delete)
- процесс рекрутинга: установка результатов тз и интервью, онбординг и оффбординг
- вычисление процента успешности прохождения этапов
- вычисление кол-ва потенциально трудоспособных людей
(от 18 лет + учитывая, что женщины выходят в 55 лет, а мужчины в 60)

Источники данных:
- CSV (Openсsv v.5.3)
- XML (Simple-xml v.2.7.1)
- JDBC (H2 v.2.0.202)

Дополнительно:
- JUnit (v.4.13.1)
- MongoDB (v.3.12.6)
- Maven

Use Case Diagram
![UseCaseDiagram](https://user-images.githubusercontent.com/49367604/147885516-0db09cbd-e125-4627-a5a2-e86018de3405.png)
[DetalizationTable.pdf](https://github.com/annmahh/kodland/files/7799566/DetalizationTable.pdf)

Class Diagram
![ClassDiagram](https://user-images.githubusercontent.com/49367604/147885517-6c01cf18-9875-4c92-904a-9348a862e56a.png)
