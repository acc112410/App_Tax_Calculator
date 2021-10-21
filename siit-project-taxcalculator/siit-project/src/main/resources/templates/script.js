class Calculator{
    constructor(incomeImput) {
        this.incomeImput = incomeImput
        // this.casOutput = incomeImput * 25 /100
        // this.cassOutput = incomeImput /10
        // this.imcomeTaxedOutput = incomeImput /10
        // this.incomeTaxesPerMonthOutput = 0
        // this.dividendTaxesPerMonthOutput = 0




    }
    updateDesplay(){
        this.casOutput.innerText =this.casOutput
        this.cassOutput.innerText = this.cassOutput
        this.imcomeTaxedOutput.innerText = this.imcomeTaxedOutput
        this.incomeTaxesPerMonthOutput = 0
        this.dividendTaxesPerMonthOutput = 0

    }
}


const submitButton= document.querySelector('[data-equals]')
const incomeImput= document.querySelector('[income]')
const  casOutput= document.querySelector('[cas]')
const  cassOutput= document.querySelector('[cass]')
const  incomeTaxesOutput= document.querySelector('[incomeTaxes]')
const  incomeTaxesPerMonthOutput= document.querySelector('[incomeTaxesPerMonth]')
const  dividendTaxesPerMonthOutput= document.querySelector('[dividendTaxesPerMonth]')

const calculator = new Calculator(incomeImput)
submitButton.for(button =>{
    button.addEventListener('click',()=>{
        const calculator = new Calculator(incomeImput)
        calculator.updateDesplay()
    })
})

