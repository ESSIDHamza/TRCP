





var app = angular.module('ProduitManagement', ['ngRoute' , 'ngAnimate', 'ui.bootstrap']);

app
    .config(function($routeProvider) {
      $routeProvider
          .when(
              "/",
              {
                templateUrl : "/sidebar.html"
              })
          .when(
              "/modifier",
              {
                templateUrl : "/modifierAgence.html"
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
              "/addProduit",
              {
                templateUrl : "/sidebarAdd.html"
              })
           .when(
              "/IA",
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
app.controller('ProduitService', function($scope, $http, $rootScope) {  
$scope.oneAtATime = true;


    $scope.status = {
      isCustomHeaderOpen: false,
      isFirstOpen: true,
      isFirstDisabled: false
    };
//  $(document).ready(function(){
//    $("#modifierTRCPurl").click(function(){
//      $("#bodyContent").attr("ng-include", "'modifierAgence.html'");
//      $("#creerTRCP").attr("class","");
//      $("#modifierTRCP").attr("class","active");
//      $("navbarContent").load("navbar.html");
//      $("#bodyContent").load("modifierAgence.html");
//
//    });
//    $("#creerTRCPurl").click(function(){
//      $("#bodyContent").attr("ng-include", "'sidebarSup.html'");
//      $("#creerTRCP").attr("class","active");
//      $("#modifierTRCP").attr("class","");
//      $("navbarContent").load("navbar.html");
//      $("#bodyContent").load("sidebarSup.html");
//
//
//    });
//  });
  


  $scope.produits = [];  
  $scope.ProduitForm = {
    referenceProduit : "" ,
    assiette :"" ,
    fiche : "fiche",
    nomEtPrenom : "nomEtPrenom",
    codeAgence : "codeAgence",
    dr : "dr",
    compte : "compte",
    dateSaisie : "",
    dateDerniereModification : "",
    dateValreferenceProduitation : "",
    valeur :"",
    tarifAccorde : "",
    echeanceAccordee : "",
    referenceDemande: ""
  };  

  $scope.demandes = [];
  $scope.DemandeForm = {
    id:"",
    referenceDemande:"",
    produits: [],
    cheminPDF:"",
    valideeAgence:false,
    valideeBO:false
  }

  function contains(a, obj) {
    for(var i = 0; i < a.length; i++) {
      if (a[i].referenceProduit == obj) {
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
    $scope.DemandeForm.cheminPDF = "C:/"+ele.files[0].name;
    console.log("aa"+$scope.DemandeForm.cheminPDF)
  }

  $scope.valider = function(){
    //if ($scope.DemandeForm.cheminPDF !='') {
      console.log("ETAT CHANGE !!")
      $scope.DemandeForm.valideeAgence = true;
    //}else 
      //alert("add pdf")
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
      var str = $scope.ProduitForm.valeur.replace('.',',');
      $scope.ProduitForm.valeur = str;   

      $scope.ProduitForm.tarifSollicite =  $scope.ProduitForm.prefixeTarifSollicite + $scope.ProduitForm.valeur + $scope.ProduitForm.sufixeTarifSollicite;

      if (referenceProduit<=52) {
        if ($scope.tab[referenceProduit].valeur>0) 
          $scope.ProduitForm.tarifSollicite = "TMM + " + $scope.ProduitForm.valeur;
        else
          $scope.ProduitForm.tarifSollicite = "TMM " + $scope.ProduitForm.valeur;
      }else
        $scope.ProduitForm.tarifSollicite = $scope.ProduitForm.valeur+ " DT";
      $scope.ProduitForm.nbrJoursPlacement = $scope.tab[referenceProduit].nbrJoursPlacement;
      $scope.ProduitForm.montantPlacement = $scope.tab[referenceProduit].montantPlacement;
      $scope.ProduitForm.tarifStandard = tarifStandard;
      $scope.ProduitForm.correspondance = correspondance;
      $scope.ProduitForm.indexTarif = indexTarif;
      $scope.ProduitForm.echeanceSollicitee = $scope.tab[referenceProduit].echeanceSollicitee;
      $scope.ProduitForm.ttcouHT = ttcouHT;
      $scope.ProduitForm.assiette = assiette;
      $scope.ProduitForm.typeValeur = typeValeur;
      $scope.ProduitForm.operation = $scope.tab[referenceProduit].operation;
      $scope.ProduitForm.pourcentageKit = $scope.tab[referenceProduit].pourcentageKit;      

      console.log("x == "+x)

      if (x===-1) {
        console.log("++avant push .."+JSON.stringify( $scope.ProduitForm));
        $scope.DemandeForm.produits.push($scope.ProduitForm)
      }else {
        alert($scope.tab[referenceProduit].operation +" already Exists");
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
        echeanceSollicitee : "",
        assiette : "",
        tarifSollicite:"",
        typeValeur : "",
        operation : "",
        pourcentageKit: "",
        referenceDemande:""
      }; 
      
    }     
  
  $scope.submitProduits = function() { 
    console.log("submitProduits ...")
    console.log("<--->"+JSON.stringify($scope.DemandeForm.produits));
    $scope.DemandeForm.valideeAgence = false;
    $scope.DemandeForm.valideeBO = false;
    $scope.DemandeForm.cheminPDF = "";


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
    }).then( _success, _error );
    
    $scope.DemandeForm = {
      valideeBO: false,
      valideeAgence: false,
      referenceDemande:"",
      id:0,
      cheminPDF:"",
      produits: []
    }
  }

  
  $scope.deleteProduit = function(produit) {  
    console.log("DELETE Produit...")
    $scope.ProduitForm = produit
    console.log("refDem : "+$scope.ProduitForm.referenceDemande)
    console.log("refProd :"+$scope.ProduitForm.referenceProduit)
    $http({  
      method : 'DELETE',  
      url : 'http://localhost:8080/api/cp/demande/'+$scope.ProduitForm.referenceDemande+'/produit/'+$scope.ProduitForm.referenceProduit ,
      headers : {  
        'Content-Type' : 'application/x-www-form-urlencoded'  
      }
    }).then(_success, _error);  
  }; 

  $scope.deleteDemande = function() {  
    console.log( "DELTE Demande : "+$scope.DemandeForm.referenceDemande);
    $http({  
      method : 'DELETE',  
      url : 'http://localhost:8080/api/cp/demande/'+$scope.DemandeForm.referenceDemande  
    }) .then(function successCallback(response) {  
      _refreshContactData($scope.DemandeForm.referenceDemande);  
      console.log("DELETE done !!") ;
    }, function errorCallback(response) {  
      console.log(response.statusText);  
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
    //      $scope.demandes[0] = $scope.DemandeForm
      console.log("GET : " + JSON.stringify($scope.demandes)) ;
    }, function errorCallback(response) {  
      console.log(response.statusText);  
    }); 
  };

  $scope.editDemandeProduit = function(produit){
   console.log("PUT... " + JSON.stringify(produit)) ;
   $scope.ProduitForm = produit;
   $http({  
     method : 'PUT',  
     url : 'http://localhost:8080/api/cp/demande/'+$scope.ProduitForm.referenceDemande+'/produit/'+$scope.ProduitForm.referenceProduit ,
     data : angular.toJson($scope.ProduitForm),  
     headers : {  
      'Content-Type' : 'application/json'  
      } 
    }).then(function successCallback(response) {  
      $scope.DemandeForm.produits = response.data; 
      _refreshContactData($scope.ProduitForm.referenceDemande);  
      console.log("PUT : " + JSON.stringify($scope.DemandeForm.produits)) ;
    }, function errorCallback(response) {  
      console.log(response.statusText);  
    }); 

  };

  $scope.getInfoProduitDemande = function(referenceDemande){
    produits = [];
    console.log("GET...")
    $http({  
      method : 'GET',  
      url : 'http://localhost:8080/api/cp/'+referenceDemande+'/produits'  
    }).then(function successCallback(response) {
    	$scope.refDem=referenceDemande;
    	$scope.actionPost = "http://localhost:8080/api/cp/"+$scope.refDem+"/dud";
      console.log("dud.."+ $scope.actionPost)
      $scope.produits = response.data; 
      $rootScope.produits = response.data; 
      console.log("GET : " + JSON.stringify($scope.produits)) ;
    }, function errorCallback(response) {  
      console.log(response.statusText);  
    }); 
  };

  $scope.editDemandeProduit = function(produit){
   console.log("PUT... " + JSON.stringify(produit)) ;
   $scope.ProduitForm = produit;
   $http({  
     method : 'PUT',  
     url : 'http://localhost:8080/api/cp/demande/'+$scope.ProduitForm.referenceDemande+'/produit/'+$scope.ProduitForm.referenceProduit ,
     data : angular.toJson($scope.ProduitForm),  
     headers : {  
      'Content-Type' : 'application/json'  
      } 
    }).then(function successCallback(response) {  
      $scope.DemandeForm.produits = response.data; 
      _refreshContactData($scope.ProduitForm.referenceDemande);  
      console.log("PUT : " + JSON.stringify($scope.DemandeForm.produits)) ;
    }, function errorCallback(response) {  
      console.log(response.statusText);  
    }); 

 
  }

  function _refreshContactData(referenceDemande) {  
    console.log("_refreshContactData..."+referenceDemande)
    if (referenceDemande != "") {
    $http({  
      method : 'GET',  
      url : 'http://localhost:8080/api/cp/'+referenceDemande  
    }).then(function successCallback(response) {  
      $scope.DemandeForm.produits = response.data;  
    }, function errorCallback(response) {  
      console.log(response.statusText);  
    });  
  }
  } 

  function _success(response) {  
    _refreshContactData($scope.DemandeForm.referenceDemande);  
    while(response.statusText != "OK") {}
    alert("Operation réussite !!") ;
    console.log(response.statusText);  

      }  

  function _error(response) {  
    while(response.statusText != "OK") {}
    alert("Operation réussite !!") ;
    console.log(response.statusText);  
  }  

  $scope.testIfPlacement = function(referenceProduit){
    var referenceProduitInt = parseInt(referenceProduit);
    //console.log("refPrd===============>" + referenceProduitInt)
    if ((referenceProduitInt >= 4) && (referenceProduitInt <= 52))
      return true
    else
      return false
  }

  $scope.getDemandesValAgences = function(){
    $scope.demandes = [];
    console.log("GET...")
    $http({  
      method : 'GET',  
      url : 'http://localhost:8080/api/cp/demandes_validees_agence' 
    }).then(function successCallback(response) {  
      $scope.demandes = response.data; 
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
      console.log(response.statusText);  
    }, function errorCallback(response) {  
      console.log(response.statusText);  
    }); 
  };

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
      //alert("GET : rootScope" + JSON.stringify($rootScope.produits)) ;
    }, function errorCallback(response) {  
      console.log(response.statusText);  
    }); 
  };

  // $scope.addTarifSollicite = function(referenceProduit,correspondance,indexTarif,ttcouHT,tarifStandard,assiette,typeValeur){
  //   if ($scope.tab[referenceProduit].valeur != null) {
  //     var referenceProduitString = referenceProduit.toString();
  //     $scope.ProduitForm.referenceProduit = referenceProduitString;

  //     var x  = contains($scope.produits, $scope.ProduitForm.referenceProduit);
      
  //     if (typeof($scope.tab[referenceProduit].valeur)==='number') {
  //       $scope.ProduitForm.valeur = $scope.tab[referenceProduit].valeur.toString();      
  //     }
  //     else{
  //       $scope.ProduitForm.valeur = $scope.tab[referenceProduit].valeur;         
  //     }
  //     var str = $scope.ProduitForm.valeur.replace('.',',');
  //     $scope.ProduitForm.valeur = str;   


  //     if (referenceProduit<=52) {
  //       if ($scope.tab[referenceProduit].valeur>0) 
  //         $scope.ProduitForm.tarifSollicite = "TMM + " + $scope.ProduitForm.valeur;
  //       else
  //         $scope.ProduitForm.tarifSollicite = "TMM " + $scope.ProduitForm.valeur;
  //     }else
  //       $scope.ProduitForm.tarifSollicite = $scope.ProduitForm.valeur+ " DT";
  //     $scope.ProduitForm.nbrJoursPlacement = $scope.tab[referenceProduit].nbrJoursPlacement;
  //     $scope.ProduitForm.montantPlacement = $scope.tab[referenceProduit].montantPlacement;
  //     $scope.ProduitForm.tarifStandard = tarifStandard;
  //     $scope.ProduitForm.correspondance = correspondance;
  //     $scope.ProduitForm.indexTarif = indexTarif;
  //     $scope.ProduitForm.echeanceSollicitee = $scope.tab[referenceProduit].echeanceSollicitee;
  //     $scope.ProduitForm.ttcouHT = ttcouHT;
  //     $scope.ProduitForm.assiette = assiette;
  //     $scope.ProduitForm.typeValeur = typeValeur;
  //     $scope.ProduitForm.operation = $scope.tab[referenceProduit].operation;
  //     $scope.ProduitForm.pourcentageKit = $scope.tab[referenceProduit].pourcentageKit;      


  //     if (x===-1) {
  //       $scope.produits.unshift($scope.ProduitForm)
  //     }else if(x===0){
  //       $scope.ProduitForm = $scope.produits[x];
  //       console.log("avant splice .."+JSON.stringify($scope.produits))
  //       $scope.produits.splice(x,1);
  //       console.log("apres splice .."+JSON.stringify($scope.produits))
  //       if (typeof($scope.tab[referenceProduit].valeur)==='number'){
  //         $scope.ProduitForm.valeur = $scope.tab[referenceProduit].valeur.toString();
  //       }else{
  //         $scope.ProduitForm.valeur = $scope.tab[referenceProduit].valeur;
  //       }
  //       var str = $scope.ProduitForm.valeur.replace('.',',');
  //       $scope.ProduitForm.valeur = str;   
        
  //       if (referenceProduit<=52) {
  //         if ($scope.tab[referenceProduit].valeur>0) 
  //           $scope.ProduitForm.tarifSollicite = "TMM + " + $scope.ProduitForm.valeur;
  //         else
  //           $scope.ProduitForm.tarifSollicite = "TMM " + $scope.ProduitForm.valeur;
  //       }else
  //         $scope.ProduitForm.tarifSollicite = $scope.ProduitForm.valeur+ " DT";

  //       $scope.produits.push($scope.ProduitForm);
  //       console.log("apres push .."+JSON.stringify($scope.produits))
  //     }else{
  //       $scope.ProduitForm = $scope.produits[x-1];
  //       console.log("avant splice .."+JSON.stringify($scope.produits))
  //       $scope.produits.splice(x-1,1);
  //       console.log("apres splice .."+JSON.stringify($scope.produits))
  //       if (typeof($scope.tab[referenceProduit].valeur)==='number')
  //         $scope.ProduitForm.valeur = $scope.tab[referenceProduit].valeur.toString();
  //       else
  //         $scope.ProduitForm.valeur = $scope.tab[referenceProduit].valeur;
  //       var str = $scope.ProduitForm.valeur.replace('.',',');
  //       $scope.ProduitForm.valeur = str;   

  //       if (referenceProduit<=52) {
  //         if ($scope.tab[referenceProduit].valeur>0) 
  //           $scope.ProduitForm.tarifSollicite = "TMM + " + $scope.ProduitForm.valeur;
  //         else
  //           $scope.ProduitForm.tarifSollicite = "TMM " + $scope.ProduitForm.valeur;
  //       }else
  //       $scope.ProduitForm.tarifSollicite = $scope.ProduitForm.valeur+ " DT";

  //       $scope.produits.push($scope.ProduitForm);
  //       console.log("apres push .."+JSON.stringify($scope.produits))
  //     }
  //     console.log(JSON.stringify($scope.produits));
  //     $scope.ProduitForm = {  
  //       referenceProduit : "" ,
  //       assiette :"" ,
  //       fiche : "fiche",
  //       nomEtPrenom : "nomEtPrenom",
  //       codeAgence : "codeAgence",
  //       dr : "dr",
  //       compte : "compte",
  //       dateSaisie : "",
  //       dateDerniereModification : "",
  //       dateValreferenceProduitation : "",
  //       valeur :"",
  //       tarifAccorde : "",
  //       echeanceAccordee : "",
  //       ///////
  //       nbrJoursPlacement : "",
  //       montantPlacement : "",
  //       tarifStandard : "",
  //       correspondance : "",
  //       indexTarif : "",
  //       ttcouHT : "",
  //       echeanceSollicitee : "",
  //       tarifSollicite:"",
  //       typeValeur : "",
  //       operation : "",
  //       pourcentageKit: "",
  //       referenceDemande:""
  //     }; 
  //   }
  // }


  // $scope.addEcheanceSollicitee = function(referenceProduit,correspondance,indexTarif,ttcouHT,tarifStandard,assiette,typeValeur){
  //   if ($scope.tab[referenceProduit].echeanceSollicitee != null) {
  //     var referenceProduitString = referenceProduit.toString();
  //     $scope.ProduitForm.referenceProduit = referenceProduitString;
  //     var x  = contains($scope.produits, $scope.ProduitForm.referenceProduit);
      
  //     $scope.ProduitForm.tarifSollicite = $scope.ProduitForm.valeur+ " DT";


  //     $scope.ProduitForm.valeur = $scope.tab[referenceProduit].valeur;

  //     $scope.ProduitForm.nbrJoursPlacement = $scope.tab[referenceProduit].nbrJoursPlacement;
  //     $scope.ProduitForm.montantPlacement = $scope.tab[referenceProduit].montantPlacement;
  //     $scope.ProduitForm.tarifStandard = tarifStandard;
  //     $scope.ProduitForm.correspondance = correspondance;
  //     $scope.ProduitForm.indexTarif = indexTarif;
  //     $scope.ProduitForm.echeanceSollicitee = $scope.tab[referenceProduit].echeanceSollicitee;
  //     $scope.ProduitForm.ttcouHT = ttcouHT;
  //     $scope.ProduitForm.assiette = assiette;
  //     $scope.ProduitForm.typeValeur = typeValeur;
  //     $scope.ProduitForm.operation = $scope.tab[referenceProduit].operation;
  //     $scope.ProduitForm.pourcentageKit = $scope.tab[referenceProduit].pourcentageKit;      

  //     if (x===-1) {
  //       $scope.produits.unshift($scope.ProduitForm)
  //     }else if(x===0){
  //       $scope.ProduitForm = $scope.produits[x];
  //       $scope.produits.splice(x,1);
  //       $scope.ProduitForm.echeanceSollicitee = $scope.tab[referenceProduit].echeanceSollicitee;
  //       $scope.produits.push($scope.ProduitForm);
  //     }else{
  //       $scope.ProduitForm = $scope.produits[x-1];
  //       $scope.produits.splice(x-1,1);
  //       $scope.ProduitForm.echeanceSollicitee = $scope.tab[referenceProduit].echeanceSollicitee;
  //       $scope.produits.push($scope.ProduitForm);
  //     }
  //     console.log(JSON.stringify($scope.produits));
  //     $scope.ProduitForm = {  
  //       referenceProduit : "",  
  //       nbrJoursPlacement : "",
  //       montantPlacement : "",
  //       valeur : "",
  //       tarifStandard : "",
  //       correspondance : "",
  //       indexTarif : "",
  //       ttcouHT : "",
  //       echeanceSollicitee : "",
  //       assiette :"",
  //       tarifSollicite :"",
  //       typeValeur :"",
  //       operation: "",
  //       pourcentageKit: "",
  //       referenceDemande:""
  //     }; 
  //   }
  // }

  // $scope.addMontantPlacement = function(referenceProduit,correspondance,indexTarif,ttcouHT,tarifStandard,assiette,typeValeur){
  //   if ($scope.tab[referenceProduit].montantPlacement != null) {
  //     var referenceProduitString = referenceProduit.toString();
  //     $scope.ProduitForm.referenceProduit = referenceProduitString;
  //     var x  = contains($scope.produits, $scope.ProduitForm.referenceProduit);
      
  //     if (typeof($scope.tab[referenceProduit].montantPlacement)==='number')
  //       $scope.ProduitForm.montantPlacement = $scope.tab[referenceProduit].montantPlacement.toString();
  //     else
  //       $scope.ProduitForm.montantPlacement = $scope.tab[referenceProduit].montantPlacement;

  //     //////
  //     $scope.ProduitForm.tarifSollicite = $scope.ProduitForm.valeur+ " DT";

  //     $scope.ProduitForm.nbrJoursPlacement = $scope.tab[referenceProduit].nbrJoursPlacement;
  //     $scope.ProduitForm.valeur = $scope.tab[referenceProduit].valeur;
  //     $scope.ProduitForm.tarifStandard = tarifStandard;
  //     $scope.ProduitForm.correspondance = correspondance;
  //     $scope.ProduitForm.indexTarif = indexTarif;
  //     $scope.ProduitForm.echeanceSollicitee = $scope.tab[referenceProduit].echeanceSollicitee;
  //     $scope.ProduitForm.ttcouHT = ttcouHT;
  //     $scope.ProduitForm.assiette = assiette;
  //     $scope.ProduitForm.typeValeur = typeValeur;
  //     $scope.ProduitForm.operation = $scope.tab[referenceProduit].operation;
  //     $scope.ProduitForm.pourcentageKit = $scope.tab[referenceProduit].pourcentageKit;      


  //     if (x===-1) {
  //       $scope.produits.unshift($scope.ProduitForm)
  //     }else if (x===0){
  //       $scope.ProduitForm = $scope.produits[x];
  //       $scope.produits.pop($scope.ProduitForm);
  //       if (typeof($scope.tab[referenceProduit].montantPlacement)==='number')
  //         $scope.ProduitForm.montantPlacement = $scope.tab[referenceProduit].montantPlacement.toString();
  //       else
  //         $scope.ProduitForm.montantPlacement = $scope.tab[referenceProduit].montantPlacement;
  //       $scope.produits.push($scope.ProduitForm);
  //     }else{
  //       $scope.ProduitForm = $scope.produits[x-1];
  //       $scope.produits.pop($scope.ProduitForm);
  //       if (typeof($scope.tab[referenceProduit].montantPlacement)==='number')
  //         $scope.ProduitForm.montantPlacement = $scope.tab[referenceProduit].montantPlacement.toString();
  //       else
  //         $scope.ProduitForm.montantPlacement = $scope.tab[referenceProduit].montantPlacement;
  //       $scope.produits.push($scope.ProduitForm);
  //     }
  //     console.log(JSON.stringify($scope.produits));
  //     $scope.ProduitForm = {  
  //       referenceProduit : "",  
  //       nbrJoursPlacement : "",
  //       montantPlacement : "",
  //       valeur : "",
  //       tarifStandard : "",
  //       correspondance : "",
  //       indexTarif : "",
  //       ttcouHT : "",
  //       echeanceSollicitee : "",
  //       assiette : "",
  //       tarifSollicite :"",
  //       typeValeur : "",
  //       operation :"",
  //       pourcentageKit: "",
  //       referenceDemande:""

  //     }; 
  //   }
  // }

  // $scope.addNbrJoursPlacement = function(referenceProduit,correspondance,indexTarif,ttcouHT,tarifStandard,assiette,typeValeur){
  //   if ($scope.tab[referenceProduit].nbrJoursPlacement != null) {
  //     var referenceProduitString = referenceProduit.toString();
  //     $scope.ProduitForm.referenceProduit = referenceProduitString;
  //     var x  = contains($scope.produits, $scope.ProduitForm.referenceProduit);
      
  //     if (typeof($scope.tab[referenceProduit].nbrJoursPlacement)==='number')
  //       $scope.ProduitForm.nbrJoursPlacement = $scope.tab[referenceProduit].nbrJoursPlacement.toString();
  //     else
  //       $scope.ProduitForm.nbrJoursPlacement = $scope.tab[referenceProduit].nbrJoursPlacement;

  //     //////
  //     $scope.ProduitForm.tarifSollicite = $scope.ProduitForm.valeur+ " DT";
  //     $scope.ProduitForm.montantPlacement = $scope.tab[referenceProduit].montantPlacement;    
  //     $scope.ProduitForm.valeur = $scope.tab[referenceProduit].valeur;
  //     $scope.ProduitForm.tarifStandard = tarifStandard;
  //     $scope.ProduitForm.correspondance = correspondance;
  //     $scope.ProduitForm.indexTarif = indexTarif;
  //     $scope.ProduitForm.echeanceSollicitee = $scope.tab[referenceProduit].echeanceSollicitee;
  //     $scope.ProduitForm.ttcouHT = ttcouHT;
  //     $scope.ProduitForm.assiette = assiette;
  //     $scope.ProduitForm.typeValeur = typeValeur;
  //     $scope.ProduitForm.operation = $scope.tab[referenceProduit].operation;
  //     $scope.ProduitForm.pourcentageKit = $scope.tab[referenceProduit].pourcentageKit;      

  //     if (x===-1) {
  //       $scope.produits.unshift($scope.ProduitForm)
  //     }else if (x===0){
  //       $scope.ProduitForm = $scope.produits[x];
  //       $scope.produits.splice(x,1);
  //       if (typeof($scope.tab[referenceProduit].nbrJoursPlacement)==='number')
  //         $scope.ProduitForm.nbrJoursPlacement = $scope.tab[referenceProduit].nbrJoursPlacement.toString();
  //       else
  //         $scope.ProduitForm.nbrJoursPlacement = $scope.tab[referenceProduit].nbrJoursPlacement;
  //       $scope.produits.push($scope.ProduitForm);
  //     }else{
  //       $scope.ProduitForm = $scope.produits[x-1];
  //       $scope.produits.splice(x-1,1);
  //       if (typeof($scope.tab[referenceProduit].nbrJoursPlacement)==='number')
  //         $scope.ProduitForm.nbrJoursPlacement = $scope.tab[referenceProduit].nbrJoursPlacement.toString();
  //       else
  //         $scope.ProduitForm.nbrJoursPlacement = $scope.tab[referenceProduit].nbrJoursPlacement;
  //       $scope.produits.push($scope.ProduitForm);
  //     }
  //     console.log(JSON.stringify($scope.produits));
  //     $scope.ProduitForm = {  
  //       referenceProduit : "",  
  //       nbrJoursPlacement : "",
  //       montantPlacement : "",
  //       valeur : "",
  //       tarifStandard : "",
  //       correspondance : "",
  //       indexTarif : "",
  //       ttcouHT : "",
  //       echeanceSollicitee : "",
  //       assiette :"",
  //       tarifSollicite:"",
  //       typeValeur: "",
  //       operation: "",
  //       pourcentageKit: "",
  //       referenceDemande:""
  //     }; 
  //   }
  // }

  // $scope.addPourcentageKit = function(referenceProduit,correspondance,indexTarif,ttcouHT,tarifStandard,assiette,typeValeur){
  //   if ($scope.tab[referenceProduit].pourcentageKit != null) {
  //     var referenceProduitString = referenceProduit.toString();
  //     $scope.ProduitForm.referenceProduit = referenceProduitString;

  //     var x  = contains($scope.produits, $scope.ProduitForm.referenceProduit);
      
  //    /////
  //    $scope.ProduitForm.tarifSollicite = $scope.ProduitForm.valeur+ " DT";
  //    $scope.ProduitForm.nbrJoursPlacement = $scope.tab[referenceProduit].nbrJoursPlacement;
  //    $scope.ProduitForm.montantPlacement = $scope.tab[referenceProduit].montantPlacement;
  //    $scope.ProduitForm.tarifStandard = tarifStandard;
  //    $scope.ProduitForm.correspondance = correspondance;
  //    $scope.ProduitForm.indexTarif = indexTarif;
  //    $scope.ProduitForm.echeanceSollicitee = $scope.tab[referenceProduit].echeanceSollicitee;
  //    $scope.ProduitForm.ttcouHT = ttcouHT;
  //    $scope.ProduitForm.assiette = assiette;
  //    $scope.ProduitForm.typeValeur = typeValeur;
  //    $scope.ProduitForm.operation = $scope.tab[referenceProduit].operation;
  //    $scope.ProduitForm.pourcentageKit = $scope.tab[referenceProduit].pourcentageKit;      


  //    if (x===-1) {
  //     $scope.produits.unshift($scope.ProduitForm)
  //   }else if (x===0){
  //     $scope.ProduitForm = $scope.produits[x];
  //     $scope.produits.splice(x,1);
  //     $scope.ProduitForm.pourcentageKit = $scope.tab[referenceProduit].pourcentageKit;
  //     $scope.produits.push($scope.ProduitForm);
  //   }else{
  //     $scope.ProduitForm = $scope.produits[x-1];
  //     $scope.produits.splice(x-1,1);
  //     $scope.ProduitForm.pourcentageKit = $scope.tab[referenceProduit].pourcentageKit;
  //     $scope.produits.push($scope.ProduitForm);
  //   }
  //   console.log(JSON.stringify($scope.produits));
  //   $scope.ProduitForm = {  
  //     referenceProduit : "",  
  //     nbrJoursPlacement : "",
  //     montantPlacement : "",
  //     valeur : "",
  //     tarifStandard : "",
  //     correspondance : "",
  //     indexTarif : "",
  //     ttcouHT : "",
  //     echeanceSollicitee : "",
  //     assiette : "",
  //     tarifSollicite:"",
  //     typeValeur : "",
  //     operation : "",
  //     pourcentageKit: "",
  //     referenceDemande:""
  //   }; 
  // }
  // }


});
