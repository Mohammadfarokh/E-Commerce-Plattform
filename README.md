verwaltung:
	- http://localhost:8080/api/verwaltung/verwaltung-add
		JSON: {
         		}
	- http://localhost:8080/api/verwaltung/verwaltung-get
	- http://localhost:8080/api/verwaltung/verwaltung-get-with-id
		JSON:	1
	- http://localhost:8080/api/verwaltung/verwaltung-get-all-transactions
kunde:
	- http://localhost:8080/api/kunde/kunde-add
		JSON:  {
       	 	  "firstname": "jack",
       		  "lastname": "sparrow",
       		  "password": "1234",
      		  "street": "emilstrasse",
        	  "city": "Dortmund",
      		  "postcode": 12345,
       		  "verwaltung":{
      		  "id": 1
  		  }
 	 	 }
		JSON:  {
        	 "firstname": "john",
     		 "lastname": "wick",
      		 "password": "123456",
     		 "street": "unistrasse",
     		 "city": "Bochum",
     		 "postcode": 44256,
     		 "verwaltung":{
      		 "id": 1
  		  }
 	         }
	- http://localhost:8080/api/kunde/konto-add
		JSON:  {
        "bankName": "volksbank",
        "iban": "DE12345678912345678912",
        "guthaben": 230.0,
        "kunden": {
            "id": 1,
            "firstname": "jack",
       	    "lastname": "sparrow",
       	    "password": "1234",
      	    "street": "emilstrasse",
            "city": "Dortmund",
      	    "postcode": 12345,
            "verwaltung": {
                "id": 1,
                "kunden": []
            },
            "kontos": []
        },
        "transactions": []
    }
		JSON:  {
        "bankName": "sparkasse",
        "iban": "DE98765432198765432198",
        "guthaben": 150.0,
        "kunden": {
            "id": 2,
            "firstname": "john",
     	    "lastname": "wick",
      	    "password": "123456",
     	    "street": "unistrasse",
     	    "city": "Bochum",
     	    "postcode": 44256,
            "verwaltung": {
                "id": 1,
                "kunden": []
            },
            "kontos": []
        },
        "transactions": []
    }	
	- http://localhost:8080/api/kunde/ueberweisen
		JSON: {       
        "senderIBAN": "DE12345678912345678912",
        "receiverIBAN": "DE98765432198765432198",
        "isDeposit": true,
        "amount": 30.0,
        "konto": {
            "id": 1,
            "bankName": "volksbank",
            "iban": "DE12345678912345678912",
            "guthaben": 230.0,
            "kunden": {
                "id": 1,
                "firstname": "jack",
       		"lastname": "sparrow",
       		"password": "1234",
      		"street": "emilstrasse",
        	"city": "Dortmund",
      		"postcode": 12345,
                "verwaltung": {
                    "id": 1,
                    "kunden": []
                },
                "kontos": []
            },
            "transactions": []
        }
    }	
	- http://localhost:8080/api/kunde/konto-uebersicht
		JSON: {
	          "id": 1,
       	 	  "firstname": "jack",
       		  "lastname": "sparrow",
       		  "password": "1234",
      		  "street": "emilstrasse",
        	  "city": "Dortmund",
      		  "postcode": 12345,
       		  "verwaltung":{
      		  "id": 1
  		  }
 	 	 }
