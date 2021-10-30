package com.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Unit test for simple App.
 */
@RunWith(DataProviderRunner.class)
public class AppTest 
{
    private WebDriver driver;
    By edadLocalizador = By.name("edad");
    By generoLocalizador = By.name("genero");
    By btn_consultarLocalizador = By.name("btn_consultar");
    By bodySelector = By.cssSelector("body");

    @DataProvider
    public static Object[][] proveedorDatos(){
        return new Object[][]{
            {"masculino", "18", "El valor de la prima anual de seguro de auto según su género y edad es: 2000 dólares" },
            {"masculino", "24", "El valor de la prima anual de seguro de auto según su género y edad es: 2000 dólares" },
            {"masculino", "25", "El valor de la prima anual de seguro de auto según su género y edad es: 1000 dólares" },
            {"masculino", "64", "El valor de la prima anual de seguro de auto según su género y edad es: 1000 dólares" },
            {"femenino", "65", "El valor de la prima anual de seguro de auto según su género y edad es: 1500 dólares" },
            {"femenino", "18", "El valor de la prima anual de seguro de auto según su género y edad es: 500 dólares" },
            {"femenino", "24", "El valor de la prima anual de seguro de auto según su género y edad es: 500 dólares" },
            {"femenino", "25", "El valor de la prima anual de seguro de auto según su género y edad es: 500 dólares" },
            {"femenino", "64", "El valor de la prima anual de seguro de auto según su género y edad es: 500 dólares" },
            {"masculino", "-1", "La edad no puede ser un valor negativo" },
            {"masculino", "0", "La edad del cotizante debe ser igual o mayor a 18 años" },
            {"femenino", "17", "La edad del cotizante debe ser igual o mayor a 18 años" },
            {"femenino", "", "Debe ingresar su edad para poder realizar la cotización" },
            {"femenino", "dieciocho", "Debe ingresar valores numéricos en el campo edad" },
            {"masculino", "18.5", "Debe ingresar valores numéricos enteros en el campo edad" }
        };
    }

    @Before
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("file:///C:/Users/Alba/OneDrive/Documentos/1Mantenimiento%20y%20pruebas/Laboratorios/Laboratorio5/SegurosAuto/app/index.html");
    }
    @Test
    @UseDataProvider("proveedorDatos")
    public void TestConsultarPrimaAnual(String genero, String edad, String salida_esperada) throws InterruptedException{
        Thread.sleep(1000);
        driver.findElement(generoLocalizador).sendKeys(genero);
        driver.findElement(edadLocalizador).sendKeys(edad);
        driver.findElement(btn_consultarLocalizador).click();
        assertEquals(salida_esperada, driver.findElement(bodySelector).getText());
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
