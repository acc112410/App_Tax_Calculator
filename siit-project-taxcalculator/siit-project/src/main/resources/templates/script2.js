function pfa() {
    var income = document.getElementById('a').value;
    var cas = parseFloat(income) * 25 /100;
    document.getElementById("cas").innerHTML = cas;
    var cass = parseFloat(income) /10
    document.getElementById("cass").innerHTML = cass;
    document.getElementById("incomeTaxes").innerHTML = cass ;
    document.getElementById("incomeTaxesPerMounth").innerHTML = 0;
    document.getElementById("dividendTaxesPerMonth").innerHTML = 0;
    window.alert(cass)

}