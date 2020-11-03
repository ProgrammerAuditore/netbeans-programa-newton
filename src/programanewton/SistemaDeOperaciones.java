package programanewton;

public class SistemaDeOperaciones {

    public double A, B, C, D, E, F, Xi, Yi, EaXr = -1, EaYr = -1;
    private double Derivada=0, Calcular=0, Numerador=0, Denominador=0, Raiz=0;

    public SistemaDeOperaciones() {
        this.A = 0;
        this.B = 0;
        this.C = 0;
        this.D = 0;
        this.E = 0;
        this.F = 0;
        this.Xi = 0;
        this.Yi = 0;
    }
    
    public SistemaDeOperaciones(double A, double B, double C, double D, double E, double F, double Xi, double Yi) {
        this.A = A;
        this.B = B;
        this.C = C;
        this.D = D;
        this.E = E;
        this.F = F;
        this.Xi = Xi;
        this.Yi = Yi;
    }
    
    public double fncDerivadaDeU_RespectoA_X() {
        Derivada = ((2 * A) * Xi) + (B * Yi);
        
        return fncRedondear(Derivada);
    }
    public double fncDerivadaDeU_RespectoA_Y() {
        Derivada = B * Xi;
        return fncRedondear(Derivada);
    }
    
    public double fncDerivadaDeV_RespectoA_X() {
        Derivada = (E * (Math.pow(Yi, 2)));
        
        return fncRedondear(Derivada);
    }
    public double fncDerivadaDeV_RespectoA_Y() {
        Derivada = ( D + ((2*E) * Xi * Yi ));
        
        return fncRedondear(Derivada);
    }
    
    
    public double fncCalcularU() {
        Calcular = ((A*(Math.pow(Xi, 2))) + (B * Xi * Yi) + (-1 * C));
        
        return fncRedondear(Calcular);
    }
    public double fncCalcularV() {
        Calcular = ( (D * Yi) + (E * Xi * (Math.pow(Yi, 2))) + (-1 * F) );

        return fncRedondear(Calcular);
    }
    
    
    public double fncCalcularXr() {
        Numerador = ( (fncCalcularU() * fncDerivadaDeV_RespectoA_Y())-(fncCalcularV() * fncDerivadaDeU_RespectoA_Y()) );
        Denominador = ( (fncDerivadaDeU_RespectoA_X() * fncDerivadaDeV_RespectoA_Y())-(fncDerivadaDeU_RespectoA_Y() * fncDerivadaDeV_RespectoA_X()) );
        
        double Xr;
        try{
            Xr = Xi - ((Numerador) / (Denominador));
        
        }catch(Exception e){
            Xr = 0;
        }
        return fncRedondear(Xr);
    }
    public double fncCalcularYr() {
        Numerador = ( (fncCalcularV() * fncDerivadaDeU_RespectoA_X())-(fncCalcularU() * fncDerivadaDeV_RespectoA_X()) );
        Denominador = ( (fncDerivadaDeU_RespectoA_X() * fncDerivadaDeV_RespectoA_Y())-(fncDerivadaDeU_RespectoA_Y() * fncDerivadaDeV_RespectoA_X()) );
        
        double Yr;
        try{
            Yr = Yi - ((Numerador) / (Denominador));
        }catch(Exception e){
            Yr = 0;
        }
        return fncRedondear(Yr);
    }

    
    public double fncCalcularRaiz1erMiembroDeLaEcuacion1(){
        Raiz = ((A * (Math.pow(Xi, 2))) + (B * Xi * Yi));
        
        return fncRedondear(Raiz);
    }
    public double fncCalcularRaiz1erMiembroDeLaEcuacion2(){
        Raiz = ((D * Yi) + (E * Xi * (Math.pow(Yi, 2))));
        
        return fncRedondear(Raiz);
    }
    

    public double fncCalcularEa(double Actual, double Anterior) {
        double ValorEa = Math.abs(((Actual - Anterior) / Actual)) * 100;
        
        // Si el resultado obtenido es NaN o Infinity ...
        // Ea se convierte en cero
        if(String.valueOf(ValorEa).contains("NaN") || String.valueOf(ValorEa).contains("Infinity") )
            return 0;
        else
            return fncRedondear(ValorEa);
    }
    
    public boolean fncVerificarRaices(){
        double resultado1 = ( fncCalcularRaiz1erMiembroDeLaEcuacion1() );
        double resultado2 = ( fncCalcularRaiz1erMiembroDeLaEcuacion2() );        
        
        // Vericamos que las raíces sean igual a C y F respectivamente ...
        if( (String.valueOf(resultado1).contains( String.valueOf(C) ) && String.valueOf(resultado2).contains( String.valueOf(F)))){
            return true;
            
        // Vericamos que las raíces sean igual a C y F respectivamente ...
        } else if( (Math.round(fncRedondear(resultado1 / C)*100)) == 100 && (Math.round(fncRedondear(resultado2 / F)*100)) == 100 ){
            return true;
            
        }

        return false;
    }
    
    private double fncRedondear(double valor){
        double resultado = Double.parseDouble(String.format("%.2f", valor ));
        
        return resultado;
    }
    
}