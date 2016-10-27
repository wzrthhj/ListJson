Exercise:  Create an app for iOS / Android that displays a list of crash names from our API.


At the end of this exercise, you should have a working app which can be compiled using XCode or Android Studio.  Provide source code in a zip file or a link to a github repo.


You'll need to use access token to retrieve a list of objects from the Crash Summary endpoint.  Each object contains a 'name' field.


You’ll need to supply an Authorization header with the Bearer type, such as Authorization: Bearer 5a839540a09f12373e52c7c82680318e


access_token: 5a839540a09f12373e52c7c82680318e


Crash Summary URL (GET):
  https://developers.crittercism.com/v1.0/app/519d53101386202089000007/crash/summaries


Example cURL:
$ curl -XGET 'https://developers.crittercism.com/v1.0/app/519d53101386202089000007/crash/summaries' -H 'Authorization: Bearer 5a839540a09f12373e52c7c82680318e'


[{
    "status": "resolved",
    "reason": "IOS signal crash payload c8f07bd3d6543118a3bc44096632b1a1",
    "displayReason": null,
    "hash": "1ecdd19f48ac4c02",
    "name": "SIGTRAP",
    "isSymbolized": "true",
    "lastOccurred": "2016-09-19T22:42:35+00:00",
    "uniqueSessionCount": 1,
    "sessionCount": 1,
    "suspectLine": null
  },
  {
    "status": "unresolved",
    "reason": "Sy6KPEISsR9JdQTCi6rY",
    "displayReason": null,
    "hash": "4df1076b89aea862",
    "name": "qe_server_crashed",
    "isSymbolized": "true",
    "lastOccurred": "2016-10-18T18:29:05+00:00",
    "uniqueSessionCount": 15,
    "sessionCount": 392295,
    "suspectLine": "-[CrittercismExampleAppViewController crashHit:] (CrittercismExampleAppViewController.m:39)"
  }]
