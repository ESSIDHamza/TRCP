var monApplication = angular.module("mon-application", []);
monApplication.controller("monControleur", function($scope, $http) {
	$http.post("http://localhost:8080/api/cp", {
		id : "1001",
		operation : "CAT DT de 3 à 12 mois",
		assiette : "",
		tarifStandard : "TMM-1",
		ttcouHT : "HT",
		indexTarif : "%+nombre de jours",
		typeValeur : "",
		montantPlacement : "2000",
		nbrJoursPlacement : "90",
		dateSaisie : "23/06/2016",
		dateDerniereModification : "24/06/2016",
		dateValidation : "25/06/2016",
		tarifSollicite : "TMM-2",
		echeanceSollicitee : "24/06/2018",
		tarifAccorde : "TMM-1",
		echeanceAccordee : "24/06/2017",
		correspondance : "C27",
		fiche : "Fich_3000",
		nomEtPrenom : "ESSID Hamza",
		codeAgence : "901",
		dr : "Sfax",
		compte : "12345678952",
		valeur : "-1"
	}).success(function() {
		alert("Output généré avec succès !");
	});
});