var app = angular.module('ProduitManagement', ['ngRoute' , 'ngAnimate', 'ui.bootstrap','ngDialog']);

app
    .config(function($routeProvider) {
      $routeProvider
          .when(
              "/",
              {
                templateUrl : "/sidebar.html"
              })
          .when(
              "/addProduit/:referenceDemande",
              {
                templateUrl : "/sidebar.html"
              })
          .when(
              "/listeDemandes",
              {
                templateUrl : "/listeDemandes.html"
              })
          .when(
              "/modifier",
              {
                templateUrl : "/modifierAgence.html"
              })
          .when(
              "/modifierBO",
              {
                templateUrl : "/modifierBO.html"
              })          
          .when(
              "/listeDemVal",
              {
                templateUrl : "/listeDemandesValideesAgence.html"
              })
           .when(
              "/test1",
              {
                templateUrl : "/test1.html"
              })
                      .when(
              "/traiterBO",
              {
                templateUrl : "/traiterBO.html"
              })
           .when(
              "/saisir",
              {
                templateUrl : "/sidebar.html"
              })
            .when(
              "/saisir/:referenceDemande",
              {
                templateUrl : "/sidebar.html"
              })
          .otherwise(
              {
                templateUrl : "/"
              });
    });

app.config(function($httpProvider) {
  $httpProvider.defaults.useXDomain = true;
});

app.run(function($rootScope) {
    $rootScope.produits = [];
})
app.controller('ProduitService', function($scope, $http, $rootScope, $routeParams,ngDialog ) { 
  $scope.produits = [];  
  $scope.ProduitForm = {
    referenceProduit : "" ,
    referenceDemande: "",
    operation :"",
    assiette :"" ,
    tarifStandard : "",
    ttcouHT : "",
    indexTarif: "",
    typeValeur:"",
    montantPlacement:"",
    nbrJoursPlacement:"",
    pourcentageKit:"",
    tarifAccorde : "",
    echeanceAccordee : "",
    correspondance :"",   
  };  

  $scope.demandes = [];
  $scope.DemandeForm = {
    id:"",
    referenceDemande:"",
    produits: [],
    cheminPDF:"",
    valideeAgence:false,
    valideeBO:false,
    rejetee: false,
    // dateSaisie : "",
    // dateDerniereModification :"",
    // dateValidationAgence :"",
    // dateValidationBO : "",
    matriculeValidationAgence : "",
    matriculeValidationBO : "",
    informationsExternes : null
  }

  $scope.informationsExternes = {
    id:"00",
    fiche: "00",
    nomEtPrenom:"00",
    codeAgence : "00",
    dr : "00",
    comptes: []
  }

  $rootScope.mail = {
    destinataire : "",
    sujet : "",
    contenu : "",
    referenceDemande  :""
  }


  $scope.popUp = function(){
  }

  $scope.popUpEnCours = function(){
    ngDialog.open({ template: 'popUpEnCours' });
  }

  $scope.popUpOperationReussie = function () {
    ngDialog.open({ template: 'popUpOperationReussie' });
  };

  $scope.popUpDoesntExist = function() {
    ngDialog.open({ template: 'popUpDoesntExist' });
  }

  $rootScope.testIfPlacement = function(){
    var ok = true;
    var produits = $rootScope.produits;
    for (var i = 0; i < produits.length ; i++) {
      var referenceProduitInt = parseInt(produits[i].referenceProduit);
      if( (referenceProduitInt >= 4) && (referenceProduitInt <= 52))
        ok = true;
      else
        ok = false;
    }
    return ok;     
  }

  $rootScope.testIfKit = function(){
    var ok = true;
    var produits = $rootScope.produits;
    for (var i = 0; i < produits.length ; i++) {
      var referenceProduitInt = parseInt(produits[i].referenceProduit);
      if( (referenceProduitInt >= 77) && (referenceProduitInt <= 91))
        ok = true;
      else
        ok = false;
    }
    return ok;  





    // var referenceProduitInt = parseInt(referenceProduit);
    // if ((referenceProduit>=77 && referenceProduit<=91)){
    //   console.log("testIfKit");
    //   return true
    // }
    // else{
    //   console.log("not testIfKit");
    //   return false
    // }
  }

  $rootScope.testNotKitNotPlacement = function(){
    if (!$rootScope.testIfKit() && !$rootScope.testIfPlacement()){
      //console.log("testNotKitNotPlacement");
      return true
    }
    else{
      //console.log("not testNotKitNotPlacement");
      return false
    }
  }

  $rootScope.testKitEtPlacement = function(){
    if ($rootScope.testIfKit() && $rootScope.testIfPlacement()){
      //console.log("testNotKitNotPlacement");
      return true
    }
    else{
      //console.log("not testNotKitNotPlacement");
      return false
    }
  }

  //si la demande est traitée par BO
  $scope.isTraitee = function(demande){
    if(demande.rejetee === false && demande.valideeBO=== false){
      //console.log(" n est pas traitée")
      return false
    } else{
      //console.log(" est traitée")
      return true
    }
  }

  //test sur btn page modifierAgence.html
  $scope.showBtnAjouterProduit = function(referenceDemande){
    if(referenceDemande === undefined ){
      //console.log("dont show"+ referenceDemande)
      return false
    }else if (($scope.DemandeForm.referenceDemande != "" ) && !$scope.isTraitee($scope.DemandeForm)
      && ($scope.DemandeForm.valideeAgence === false) ) {
      //console.log("show btn"+$scope.DemandeForm.referenceDemande)
      return true
    }else {
      //console.log("--------"+)
      return false

    }    
  }

  //test sur btn page modifierAgence.html
  $scope.showBtnValider = function(referenceDemande){
    if(referenceDemande === undefined ){
      //console.log("dont show"+ referenceDemande)
      return false
    }else if (($scope.DemandeForm.referenceDemande != "" ) && (!$scope.isTraitee($scope.DemandeForm) )
      && ($scope.DemandeForm.valideeAgence === false) ){
      //console.log("show btn"+$scope.DemandeForm.referenceDemande)
      return true
    }else {
      //console.log("--------" + $scope.DemandeForm.referenceDemande + $scope.DemandeForm.valideeAgence)
      return false

    }    
  }

  //test sur btn page modifierAgence.html
  $scope.showBtnImprimer = function(referenceDemande){
    if( referenceDemande === undefined){
      //console.log("dont show"+$scope.DemandeForm.referenceDemand)
      return false
    }else if ($scope.DemandeForm.referenceDemande != "" ) {
      //console.log("show btn"+$scope.DemandeForm.referenceDemand)
      return true
    }else
      return false
  }

  //test sur btn page modifierAgence.html
  $scope.showBlocRattacherDUD = function(referenceDemande){
    if(referenceDemande === undefined ){
      //console.log("dont show"+ referenceDemande)
      return false
    }else if (($scope.DemandeForm.referenceDemande != "" ) && (!$scope.isTraitee($scope.DemandeForm) )
      && ($scope.DemandeForm.valideeAgence === false) ){
      //console.log("show btn"+$scope.DemandeForm.referenceDemande)
      return true
    }else {
      //console.log("--------" + $scope.DemandeForm.referenceDemande + $scope.DemandeForm.valideeAgence)
      return false

    }  
  }

  $scope.showInputRecherche = function (){
    if ($scope.demandes.length === 0) {
      //console.log("false");
      return false;
    } else{
      //console.log("true"+JSON.stringify($scope.demandes));
      return true;
    }    
  }

  function contains(a, obj) {
    for(var i = 0; i < a.length; i++) {
      if (a[i].referenceProduit === obj) {
        return i;
      }
    }
    return -1
  }

  $scope.fileNameChanged = function (ele) {
    var files = ele.files;
    var l = files.length;
    var namesArr = [];

    for (var i = 0; i < l; i++) {
      namesArr.push(files[i].name);
      console.log("names : "+files[i].name)
    }
    //$scope.DemandeForm.cheminPDF = "C:/"+ele.files[0].name;
    //console.log("aa"+$scope.DemandeForm.cheminPDF)
  }


  $rootScope.validerAgence = function(){
    //console.log("ETAT CHANGE ---!!"+JSON.stringify($rootScope.DemandeForm))
     
    $rootScope.DemandeForm.valideeAgence = true;
    $rootScope.DemandeForm.dateValidationAgence = new Date();
    //$rootScope.console.log(JSON.stringify($rootScope.DemandeForm))
    var method = "";  
    var url = "";  
    method = "PUT";  
    url = 'http://localhost:8080/api/cp/demande/'+$rootScope.DemandeForm.referenceDemande;  

    $http({  
          method : method,  
          url : url,  
          data : angular.toJson($rootScope.DemandeForm),  
          headers : {  
            'Content-Type' : 'application/json'  
          }  
    }).then(function successCallback(response) {  
      _refreshContactData($rootScope.referenceDemande );
      $rootScope.getProduits($rootScope.referenceDemande);
      $scope.popUpOperationReussie();
    }, function errorCallback(response) {  
      console.log(response.statusText);  
    }); 
  }

  $rootScope.validerBO = function(DemandeForm){
       console.log("ETAT CHANGE !!")
       
        DemandeForm.valideeBO = true;
        DemandeForm.rejetee = false;
        DemandeForm.dateValidationBO = new Date();
        console.log("Valider BO : " + JSON.stringify(DemandeForm))
        var method = "";  
          var url = "";  
          method = "PUT";  
          url = 'http://localhost:8080/api/cp/demande/'+DemandeForm.referenceDemande;  


          $http({  
            method : method,  
            url : url,  
            data : angular.toJson(DemandeForm),  
            headers : {  
              'Content-Type' : 'application/json'  
            }  
          }).then( _success, _error );
  }

  $rootScope.rejeterBO = function(DemandeForm){
        DemandeForm.rejetee = true;
        DemandeForm.valideeBO = false;
        DemandeForm.dateValidationBO = new Date();
        console.log("Valider BO : " + JSON.stringify(DemandeForm))
        var method = "";  
          var url = "";  
          method = "PUT";  
          url = 'http://localhost:8080/api/cp/demande/'+DemandeForm.referenceDemande;  


          $http({  
            method : method,  
            url : url,  
            data : angular.toJson(DemandeForm),  
            headers : {  
              'Content-Type' : 'application/json'  
            }  
          }).then( _success, _error );
  }

  $scope.addProduitToList = function (referenceProduit,correspondance,indexTarif,ttcouHT,tarifStandard,assiette,typeValeur){
      var referenceProduitString = referenceProduit.toString();
      $scope.ProduitForm.referenceProduit = referenceProduitString;

      var x  = contains($scope.DemandeForm.produits, $scope.ProduitForm.referenceProduit);
      
      if (typeof($scope.tab[referenceProduit].valeur)==='number') {
        $scope.ProduitForm.valeur = $scope.tab[referenceProduit].valeur.toString();      
      }
      else{
        $scope.ProduitForm.valeur = $scope.tab[referenceProduit].valeur;         
      }

      // //car kit est un %, il n'y a pas de ,
      // if (!(referenceProduit>=77 && referenceProduit<=91)) {
      //   var str = $scope.ProduitForm.valeur.replace('.',',');
      //   $scope.ProduitForm.valeur = str;   
      // }

      //$scope.ProduitForm.tarifAccorde =  $scope.ProduitForm.prefixeTarifSollicite + $scope.ProduitForm.valeur + $scope.ProduitForm.sufixeTarifSollicite;

      if (referenceProduit<=52) {
        if ($scope.tab[referenceProduit].valeur>0) 
          $scope.ProduitForm.tarifAccorde = "TMM + " + $scope.ProduitForm.valeur+ " %";
        else
          $scope.ProduitForm.tarifAccorde = "TMM " + $scope.ProduitForm.valeur+ " %";
      }else if ((referenceProduit>52 && referenceProduit<=73) ||(referenceProduit>=92 && referenceProduit<=95) ) {
        $scope.ProduitForm.tarifAccorde = $scope.ProduitForm.valeur+ " DT";
      }else if ((referenceProduit>73 && referenceProduit<=76) || (referenceProduit>=102 &&referenceProduit<=105)) {
        $scope.ProduitForm.tarifAccorde = $scope.ProduitForm.valeur+ " DT/an";
      }else if ((referenceProduit>=77 && referenceProduit<=91)) {
        $scope.ProduitForm.tarifAccorde = $scope.ProduitForm.valeur+ " DT TTC";
      }else if ((referenceProduit===96)) {
        $scope.ProduitForm.tarifAccorde = $scope.ProduitForm.valeur+ " DT TTC/mois";
      }else if ((referenceProduit===97)) {
        $scope.ProduitForm.tarifAccorde = $scope.ProduitForm.valeur+ " DT HT/mois";
      }else if ((referenceProduit===98)) {
        $scope.ProduitForm.tarifAccorde = $scope.ProduitForm.valeur+ " DT HT/an";
      }else if ((referenceProduit>=99 && referenceProduit<=101)) {
        $scope.ProduitForm.tarifAccorde = $scope.ProduitForm.valeur+ " DT/trim";
      }else if ((referenceProduit>=106 && referenceProduit<=107)) {
        $scope.ProduitForm.tarifAccorde = $scope.ProduitForm.valeur+ " DT TTC/an";
      }else if ((referenceProduit>=137 && referenceProduit<=152) ||
        (referenceProduit===152)||(referenceProduit===154)||(referenceProduit===156)||
        (referenceProduit===158)||(referenceProduit===160)||(referenceProduit===162)||
        (referenceProduit===164)||(referenceProduit===166)||(referenceProduit===168)||
        (referenceProduit===170)||(referenceProduit===172)||(referenceProduit===174)||
        (referenceProduit===176)||(referenceProduit===178)) {
        $scope.ProduitForm.tarifAccorde = $scope.ProduitForm.valeur+ " DT + RFJ";
      }else if ((referenceProduit===153)||(referenceProduit===155)||(referenceProduit===157)||
        (referenceProduit===159)||(referenceProduit===161)||(referenceProduit===163)||
        (referenceProduit===165)||(referenceProduit===167)||
        (referenceProduit===169)||(referenceProduit===171)||(referenceProduit===173)||
        (referenceProduit===175)||(referenceProduit===177)||(referenceProduit===179)) {
        $scope.ProduitForm.tarifAccorde = $scope.ProduitForm.valeur+ " ‰ / mois";
      }else if ((referenceProduit>=108 && referenceProduit<=136)) {
        $scope.ProduitForm.tarifAccorde = "J+ " +$scope.ProduitForm.valeur;
      }else {
        $scope.ProduitForm.tarifAccorde = $scope.ProduitForm.valeur+" DT";
      }
      $scope.ProduitForm.nbrJoursPlacement = $scope.tab[referenceProduit].nbrJoursPlacement;
      $scope.ProduitForm.montantPlacement = $scope.tab[referenceProduit].montantPlacement;
      $scope.ProduitForm.tarifStandard = tarifStandard;
      $scope.ProduitForm.correspondance = correspondance;
      $scope.ProduitForm.indexTarif = indexTarif;
      $scope.ProduitForm.echeanceAccordee = $scope.tab[referenceProduit].echeanceAccordee;
      $scope.ProduitForm.ttcouHT = ttcouHT;
      $scope.ProduitForm.assiette = assiette;
      $scope.ProduitForm.typeValeur = typeValeur;
      $scope.ProduitForm.operation = $scope.tab[referenceProduit].operation;
      $scope.ProduitForm.pourcentageKit = $scope.tab[referenceProduit].pourcentageKit;
      $scope.ProduitForm.valeurAlgo = $scope.tab[referenceProduit].valeurAlgo;      


      //si kit mettre dans valeur, pourcentageKit
      if (referenceProduit>=77 &&referenceProduit <=91) {
        $scope.ProduitForm.valeur = $scope.tab[referenceProduit].pourcentageKit;
      };

      //si II-F valeur*0.1
      if (referenceProduit == 252 || referenceProduit == 253) {
        var temp = $scope.tab[referenceProduit].valeur * 0.1;
        $scope.ProduitForm.valeur = temp.toString();
      }; 

      //ttc --> ht
      if (correspondance=="A41" || correspondance=="A52" || correspondance =="A46") {
        var temp = $scope.tab[referenceProduit].valeur / 1.18;
        $scope.ProduitForm.valeur = temp.toString();
      };
      if(typeValeur == "ALGO"){
        console.log("algo!!!")
        $scope.ProduitForm.valeur = $scope.tab[referenceProduit].valeurAlgo;      
      }
      console.log("x == "+x)

      if (x===-1) {
        //console.log("++avant push .."+JSON.stringify( $scope.ProduitForm));
        $scope.DemandeForm.produits.push($scope.ProduitForm)
      }else {
        $scope.DemandeForm.produits.splice(x,1);
        // $scope.DemandeForm.produits.pop($scope.ProduitForm);
        $scope.DemandeForm.produits.push($scope.ProduitForm);
       // alert($scope.tab[referenceProduit].operation +" already Exists");
      }
      console.log(JSON.stringify( $scope.DemandeForm.produits));
      $scope.ProduitForm = {  
        referenceProduit : "",  
        nbrJoursPlacement : "",
        montantPlacement : "",
        valeur : "",
        tarifStandard : "",
        correspondance : "",
        indexTarif : "",
        ttcouHT : "",
        echeanceAccordee : "",
        assiette : "",
        tarifAccorde:"",
        typeValeur : "",
        operation : "",
        pourcentageKit: "",
        referenceDemande:"",
        valeurAlgo: ""
      }; 
      
  }     
  
  $scope.submitProduits = function() { 
    //console.log("submitProduits ...")
    //console.log("<--->"+JSON.stringify($scope.DemandeForm.produits));
    
    if ($routeParams.referenceDemande == undefined ) {
      console.log("New new !!");
      $scope.DemandeForm.valideeAgence = false;
      $scope.DemandeForm.valideeBO = false;
      $scope.DemandeForm.rejetee = false;
      //$scope.DemandeForm.cheminPDF = "page de garde finale.pdf";
      $scope.DemandeForm.dateSaisie = new Date();
      $scope.DemandeForm.informationsExternes = $scope.informationsExternes;
      $rootScope.cheminPDF = $scope.DemandeForm.cheminPDF;
      console.log("Demande : "+JSON.stringify($scope.DemandeForm))
      var method = "";  
      var url = "";  
      method = "POST";  
      url = 'http://localhost:8080/api/cp';  

      $http({  
        method : method,  
        url : url,  
        data : angular.toJson($scope.DemandeForm),  
        headers : {  
          'Content-Type' : 'application/json'  
        }  
      }).then(function successCallback(response) {  
        $scope.popUpEnCours();
        _refreshContactData(response.data.referenceDemande); 
        
        while(response.statusText != "OK") {}
        $scope.popUpOperationReussie();
        //console.log(response.statusText + "++++" + response.data.referenceDemande);
        $rootScope.cheminPDF = response.data.cheminPDF;
        $rootScope.referenceDemande = response.data.referenceDemande
        console.log("----"+$rootScope.cheminPDF)

        }, function errorCallback(response) {  
          console.log(response.statusText);  
        }); 
    }else{
      $scope.DemandeForm.referenceDemande = $routeParams.referenceDemande;

      console.log("Old old !!"+$scope.DemandeForm.referenceDemande)
      //$scope.DemandeForm.rejetee = false;
      $scope.DemandeForm.dateDerniereModification = new Date();
      for(var i = 0;i<$scope.DemandeForm.produits.length;i++){
        $scope.DemandeForm.produits[i].referenceDemande = $scope.DemandeForm.referenceDemande;
        var method = "";  
        var url = "";  
        method = "PUT";  
        console.log("old old"+$scope.DemandeForm.produits[i].referenceProduit)
        console.log("old old"+JSON.stringify($scope.DemandeForm.produits[i]))
        url = 'http://localhost:8080/api/cp/demandes/'+$scope.DemandeForm.referenceDemande;  

        $http({  
          method : method,  
          url : url,  
          data : angular.toJson($scope.DemandeForm.produits[i]),  
          headers : {  
            'Content-Type' : 'application/json'  
          }  
        }).then( _success, _error );
      }
    }
    $scope.DemandeForm = {
      valideeBO: false,
      valideeAgence: false,
      referenceDemande:"",
      id:0,
      cheminPDF:"",
      produits: []
    }
  }
  
  $rootScope.deleteProduit = function(produit) {  
    console.log("DELETE Produit...")

    // console.log("refDem : "+$scope.ProduitForm.referenceDemande)
    // console.log("refProd :"+$scope.ProduitForm.referenceProduit)
    $http({  
      method : 'DELETE',  
      url : 'http://localhost:8080/api/cp/demande/'+$rootScope.referenceDemande+'/produit/'+produit.referenceProduit ,
      headers : {  
        'Content-Type' : 'application/x-www-form-urlencoded'  
      }
    }).then(function successCallback(response) {  
      _refreshContactData($rootScope.referenceDemande );
      $rootScope.getProduits($rootScope.referenceDemande);
    }, function errorCallback(response) {  
      console.log(response.statusText);  
    }); 
  }; 

  $rootScope.deleteDemande = function() {  
    console.log( "DELTE Demande : "+$rootScope.referenceDemande);

    $http({  
      method : 'DELETE',  
      url : 'http://localhost:8080/api/cp/demande/'+$rootScope.referenceDemande  
    }).then(function successCallback(response) {  
      console.log(response.statusText); 
      if ($rootScope.referenceDemande === undefined) {
        $scope.popUpDoesntExist();
      }else{
        $scope.popUpOperationReussie();
        _refreshContactData($rootScope.referenceDemande);
      }
        
    }, function errorCallback(response) {  
      console.log(response.statusText); 
      $scope.popUpDoesntExist();
    

    }); 
  };

  $scope.getDemande = function(){
    $scope.demandes = [];
    console.log("GET...")
    $http({  
      method : 'GET',  
      url : 'http://localhost:8080/api/cp/'+$scope.DemandeForm.referenceDemande  
    }).then(function successCallback(response) {  
      $scope.demandes = response.data; 
      $scope.DemandeForm = $scope.demandes[0]
      console.log("GET : " + JSON.stringify($scope.demandes)) ;
    }, function errorCallback(response) {  
      console.log(response.statusText);  
    }); 
  };

  $rootScope.getProduits = function(referenceDemande){
    produits = [];
    console.log("GET...")
     
    $http({  
      method : 'GET',  
      url : 'http://localhost:8080/api/cp/'+referenceDemande+'/produits'  
    }).then(function successCallback(response) {
      $scope.produits = response.data; 
      $rootScope.produits = response.data; 
      $rootScope.referenceDemande = referenceDemande;
      console.log("--->"+JSON.stringify($scope.produits))
    }, function errorCallback(response) {  
      console.log(response.statusText);  
    }); 
  };

  $scope.getInfoProduitDemande = function(demande){
    produits = [];
    console.log("GET...")
    $http({  
      method : 'GET',  
      url : 'http://localhost:8080/api/cp/'+demande.referenceDemande  
      }).then(function successCallback(response) {  
      $scope.demandes = response.data; 
      $scope.DemandeForm = $scope.demandes[0];
      $rootScope.DemandeForm = $scope.demandes[0];
      //console.log("GET : " + (JSON.stringify($scope.DemandeForm))) ;
      if ($scope.DemandeForm === undefined) {
        $scope.popUpDoesntExist();
      };
    }, function errorCallback(response) {  
      console.log("GET : NOT " + (JSON.stringify($scope.DemandeForm))) ;
      console.log(response.statusText);  
    }); 
    $http({  
      method : 'GET',  
      url : 'http://localhost:8080/api/cp/'+demande.referenceDemande+'/produits'  
    }).then(function successCallback(response) {
      $scope.refDem=demande.referenceDemande;
      $scope.actionPost = "http://localhost:8080/api/cp/"+demande.referenceDemande+"/dud";
      //console.log("dud.."+ $scope.actionPost)
      $scope.produits = response.data; 
      $rootScope.produits = response.data; 
      $rootScope.referenceDemande = demande.referenceDemande;
      if ($scope.DemandeForm === undefined) {
        $scope.popUpDoesntExist();
      };
    }, function errorCallback(response) {  
      console.log(response.statusText);  
    }); 
  };

  $scope.getAllDemandes = function (){
    //console.log("get all")
    $scope.demandes = [];
    $http({  
      method : 'GET',  
      url : 'http://localhost:8080/api/cp/all_demandes' 
    }).then(function successCallback(response) {  
      $scope.demandes = response.data; 
      $rootScope.produits = $scope.DemandeForm.produits;
      //console.log(JSON.stringify($scope.demandes))
      
    }, function errorCallback(response) {  
      console.log(response.statusText);  
    }); 
  }

  $scope.sendDUD = function (){
    $('#validerDUD').submit(function () {
          return false;
    });
  }

  $rootScope.sendMail = function (){
      $rootScope.mail.destinataire = "asmadiouri@yahoo.fr";
      //$rootScope.demande.valideeAgence ---> statut
      $rootScope.mail.sujet = "CP "+$rootScope.demande.referenceDemande+" "
        +$rootScope.demande.valideeAgence;
      $rootScope.mail.referenceDemande = $rootScope.demande.referenceDemande
      var method = "";  
      var url = "";  
      method = "POST";  
      url = 'http://localhost:8080/api/cp/mail';  
      console.log(JSON.stringify($rootScope.mail))

      $http({  
        method : method,  
        url : url,  
        data : angular.toJson($rootScope.mail),  
        headers : {  
          'Content-Type' : 'application/json'  
        }  
      }).then( _success, _error );
  }

  $scope.rattacherDUD = function (referenceDemande){
      var method = "";  
      var url = "";  
      method = "POST";  
      url = 'http://localhost:8080/api/cp/'+referenceDemande+'/dud';  


      $http({  
        method : method,  
        url : url,  
        //data : angular.toJson($scope.DemandeForm),  
        headers : {  
          'Content-Type' : 'multipart/form-data'  
        }  
      }).then( _success, _error );
  }

  function _refreshContactData(referenceDemande) {  
    console.log("_refreshContactData..."+referenceDemande)
    if (referenceDemande != "") {
    $http({  
      method : 'GET',  
      url : 'http://localhost:8080/api/cp/'+referenceDemande  
    }).then(function successCallback(response) { 
      $rootScope.produits = response.data;
      $scope.produits = response.data; 
      $scope.DemandeForm.produits = response.data; 
      //console.log("------>"+JSON.stringify($rootScope.produits)) 
    }, function errorCallback(response) {  
      console.log(response.statusText);  
    });  
    }
  } 

  function _success(response) {  
    _refreshContactData($scope.DemandeForm.referenceDemande); 
    $scope.popUpOperationReussie();
    while(response.statusText != "OK") {}
    
    //alert("Opération réussie !!") ;
    console.log(response.statusText);  
  }  

  function _error(response) {  
    $scope.popUpDoesntExist()
    console.log(response.statusText);  
  }  

  $scope.getDemandesValAgences = function(){
    $scope.demandes = [];
    console.log("GET...")
    $http({  
      method : 'GET',  
      url : 'http://localhost:8080/api/cp/demandes_validees_agence' 
    }).then(function successCallback(response) {  
      $scope.demandes = response.data; 
      var temp = 0;
      for(var i = 0; i< $scope.demandes.length;i++){
        if ($scope.demandes[i].rejetee == false && $scope.demandes[i].valideeBO== false) {
          temp ++
        };
      }

      $scope.nbrDemATraiter = temp +" demandes à traiter";
      console.log($scope.nbrDemATraiter +"+++"+temp)
      //console.log("GET : " + JSON.stringify($scope.demandes)) ;
    }, function errorCallback(response) {  
      console.log(response.statusText);  
    }); 
  };

  $scope.genererFichIntegration = function(){
    console.log("genererFichIntegration...")
    $http({  
      method : 'GET',  
      url : 'http://localhost:8080/api/cp/fichier_integration' 
    }).then(function successCallback(response) { 
      alert("Fichier d'intégration généré avec succés !!") 
      console.log(response.statusText);  
    }, function errorCallback(response) {  
      console.log(response.statusText);  
    }); 
  };

  $scope.getCheminPDF=function(demande){
    $rootScope.cheminPDF = demande.cheminPDF;
  }

  $scope.getInfoProduitDemandeBO = function(demande){
    $rootScope.produits = [];
    console.log("GET..."+ JSON.stringify($rootScope.produits));
    $http({  
      method : 'GET',  
      url : 'http://localhost:8080/api/cp/'+demande.referenceDemande+'/produits'  
    }).then(function successCallback(response) {
      $scope.refDem=demande.referenceDemande;
      $scope.produits = response.data; 
      $rootScope.produits = response.data;
      $rootScope.cheminPDF = demande.cheminPDF;
      $rootScope.demande = demande;
      //alert("GET : rootScope" + JSON.stringify($rootScope.produits)) ;
    }, function errorCallback(response) {  
      console.log(response.statusText);  
    }); 
  };
});