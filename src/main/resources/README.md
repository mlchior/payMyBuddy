# PayMyBuddy

Projet 6 Openclassrooms

## Diagramme

### JACoCo
![Capture d’écran 2023-05-12 à 11.39.23.png](Capture%20d%E2%80%99%C3%A9cran%202023-05-12%20%C3%A0%2011.39.23.png)
#### Note
- Utiliser le script data.sql pour créer la base de données et les tables.
- Utiliser la fonction updateExistingPasswords() pour crypter les mots de passe déjà existants dans la base de données.
  - fichier properties 
    spring.datasource.url=jdbc:mysql://localhost:3306/payMyBuddy?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
    spring.datasource.username= ?your username?
    spring.datasource.password= ?your password?
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect
    spring.jpa.hibernate.ddl-auto=none
    server.port=8080



