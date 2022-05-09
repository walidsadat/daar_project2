# Important

Ce projet a été réalisé par *Amine BENSLIMANE* et *Walid SADAT* dans le cadre de l'UE Développement des Algorithmes d’Application Réticulaire à Sorbonne Université.

Ce projet consiste à indexer des CVs issus de plusieurs formats dans Elasticsearch afin d’être en mesure de les requêter par la suite.

## Backend
Pour executer le *backend*, lancez d'abord **Elasticsearch** (sur le port **9200** par défaut).

Si Elasticsearch n'est pas lancé sur le port **9200**, modifiez la valeur de **ELASTICSEARCH_URL** dans le fichier `./src/main/java/com/daar/elasticsearch/configuration/Config.java` pour la faire correspondre au port d'**Elasticsearch**.

Lancez le fichier main `./src/main/java/com/daar/elasticsearch/ElasticsearchApplication.java`.

Il faut télécharger le fichier jar PDFBOX et l'ajouter comme libraire externe : https://www.apache.org/dyn/closer.lua/pdfbox/2.0.24/pdfbox-app-2.0.24.jar

## Frontend
Pour executer le *frontend* exécutez les commandes suivante :
```
$ cd client
$ npm install
$ npm start
```

**npm install** installe toutes les dépendances incluses dans le ficher **package.json**.

**npm start** va deployer le client.

Si vous rencontrez des problèmes de CORS c'est que le front n'est pas lancé sur le port **3000** et dans ce cas là, modifiez la valeur de **PORT_ALLOWED** dans le fichier `./src/main/java/com/daar/elasticsearch/ElasticsearchApplication.java` ainsi que la valeur de **CLIENT_URL** dans le fichier `./client/src/App.js` pour les faire correspondre au port du frontend.

Si le backend est lancé sur un autre port que **8080**, modifiez la valeur de **API_URL** dans le fichier `./client/src/App.js` pour la faire correspondre au port du backend.
