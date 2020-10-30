package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    //Konstruktorien testit
    
    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriLuoTurhanVarastonJosAnnettuTilavuusNegatiivinen() {
        Varasto testiVarasto = new Varasto(-500);
        assertEquals(0, testiVarasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void kuormitettuKonstruktoriAsettaaTilavuudenOikein() {
        Varasto testiVarasto = new Varasto(10,2);
        assertEquals(10, testiVarasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void kuormitettuKonstruktoriLuoTurhanVarastonJosAnnettuTilavuusNegatiivinen() {
        Varasto testiVarasto = new Varasto(-10,2);
        assertEquals(0, testiVarasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void kuormitettuKonstruktoriAsettaaSaldonOikein() {
        Varasto testiVarasto = new Varasto(10,2);
        assertEquals(2, testiVarasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void kuormitettuKonstruktoriNollaaNegatiivisenAlkusaldon() {
        Varasto testiVarasto = new Varasto(10,-13000);
        assertEquals(0, testiVarasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void kuormitetunKonstruktorinSaldoEiVoiMennäYliTilavuuden() {
        Varasto testiVarasto = new Varasto(10,13);
        assertEquals(10, testiVarasto.getSaldo(), vertailuTarkkuus);
    }
    
    //Varastoon lisäämisen testit
    
    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenLisaysEiMuutaSaldoa() {
        varasto.lisaaVarastoon(-8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void varastoTäyttyyJosLisääYliTilavuuden() {
        varasto.lisaaVarastoon(8000000);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    // Varastosta ottamisen testit

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenOttaminenEiOtaMitään() {
        varasto.lisaaVarastoon(8);
        varasto.otaVarastosta(-300);
        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void saldoaEnemmänOttaminenTyhjentääVaraston() {
        varasto.lisaaVarastoon(8);
        varasto.otaVarastosta(50);
        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    //Muut testit
    
    @Test
    public void toStringToimii() {
        varasto.lisaaVarastoon(4);
        assertEquals("saldo = 4.0, vielä tilaa 6.0", varasto.toString());
    }

}