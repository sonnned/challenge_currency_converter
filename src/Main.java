import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static double APIFetcher(String code_1, String code_2) {
        final String API_URL = String.format("https://v6.exchangerate-api.com/v6/85d530e8e49760f4a670f727/pair/%s/%s", code_1, code_2);
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL))
                    .build();
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            String json = response.body();
            Gson gson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .create();
            ExchangeRate exchangeRate = gson.fromJson(json, ExchangeRate.class);
            ExchangeRateValue exchangeRateValue = new ExchangeRateValue(exchangeRate);
            return exchangeRateValue.getRate();
        } catch (Exception e) {
            System.out.println("Ocurrio un error");
            System.out.println(e.getMessage());
        }
        return 0.0;
    }
    private static boolean verificarDineroValido(double dinero) {
        return dinero > 0;
    }
    public static void main(String[] args) {
        String mensajeBienvenida = """
                Conversor de moneda\n\n\t1) Dolar => Peso argentino\n\t2) Peso argentino => Dolar\n\t3) Dolar => Real brasileño\n\t4) Real brasileño => Dolar\n\t5) Dolar => Peso colombiano\n\t6) Peso colombiano => Dolar\n\t7) Ver historial\n\t8) Salir\n\nEliga una opcion valida: 
                """;
        String mensajeOpcionValida = """
                Ingrese el valor que desea converter: 
                """;
        String mensajeOpcionInvalida = """
                Opcion invalida, por favor intentelo de nuevo 
                """;
        String mensajeSalida = """
                Saliendo del sistema...
                """;
        final String[] CODIGOS_MONEDAS_DISPONIBLES = { "ARS", "BRL", "COP", "USD" };

        int option = 0;
        Scanner scanner = new Scanner(System.in);
        ArrayList<History> history = new ArrayList<>();
        while (option != 8) {
            System.out.println(mensajeBienvenida);
            option = scanner.nextInt();
            double dineroAConvertir = 0.0;
            double factorDeCambio = 0.0;
            switch (option) {
                case 1:
                    while (!verificarDineroValido(dineroAConvertir)) {
                        System.out.println(mensajeOpcionValida);
                        dineroAConvertir = scanner.nextInt();
                        factorDeCambio = APIFetcher(CODIGOS_MONEDAS_DISPONIBLES[3], CODIGOS_MONEDAS_DISPONIBLES[0]);
                        history.add(new HistoryClass(CODIGOS_MONEDAS_DISPONIBLES[3], CODIGOS_MONEDAS_DISPONIBLES[0], dineroAConvertir, factorDeCambio * dineroAConvertir));
                        System.out.println(String.format("El valor %.2f [%s] correponde al valor final de ==>>> %.2f [%s]", dineroAConvertir, CODIGOS_MONEDAS_DISPONIBLES[3], factorDeCambio * dineroAConvertir, CODIGOS_MONEDAS_DISPONIBLES[0]));
                    }
                    break;
                case 2:
                    while (!verificarDineroValido(dineroAConvertir)) {
                        System.out.println(mensajeOpcionValida);
                        dineroAConvertir = scanner.nextInt();
                        factorDeCambio = APIFetcher(CODIGOS_MONEDAS_DISPONIBLES[0], CODIGOS_MONEDAS_DISPONIBLES[3]);
                        history.add(new HistoryClass(CODIGOS_MONEDAS_DISPONIBLES[0], CODIGOS_MONEDAS_DISPONIBLES[3], dineroAConvertir, factorDeCambio * dineroAConvertir));
                        System.out.println(String.format("El valor %.2f [%s] correponde al valor final de ==>>> %.2f [%s]", dineroAConvertir, CODIGOS_MONEDAS_DISPONIBLES[0], factorDeCambio * dineroAConvertir, CODIGOS_MONEDAS_DISPONIBLES[3]));
                    }
                    break;
                case 3:
                    while (!verificarDineroValido(dineroAConvertir)) {
                        System.out.println(mensajeOpcionValida);
                        dineroAConvertir = scanner.nextInt();
                        factorDeCambio = APIFetcher(CODIGOS_MONEDAS_DISPONIBLES[3], CODIGOS_MONEDAS_DISPONIBLES[1]);
                        history.add(new HistoryClass(CODIGOS_MONEDAS_DISPONIBLES[3], CODIGOS_MONEDAS_DISPONIBLES[1], dineroAConvertir, factorDeCambio * dineroAConvertir));
                        System.out.println(String.format("El valor %.2f [%s] correponde al valor final de ==>>> %.2f [%s]", dineroAConvertir, CODIGOS_MONEDAS_DISPONIBLES[3], factorDeCambio * dineroAConvertir, CODIGOS_MONEDAS_DISPONIBLES[1]));
                    }
                    break;
                case 4:
                    while (!verificarDineroValido(dineroAConvertir)) {
                        System.out.println(mensajeOpcionValida);
                        dineroAConvertir = scanner.nextInt();
                        factorDeCambio = APIFetcher(CODIGOS_MONEDAS_DISPONIBLES[1], CODIGOS_MONEDAS_DISPONIBLES[3]);
                        history.add(new HistoryClass(CODIGOS_MONEDAS_DISPONIBLES[1], CODIGOS_MONEDAS_DISPONIBLES[3], dineroAConvertir, factorDeCambio * dineroAConvertir));
                        System.out.println(String.format("El valor %.2f [%s] correponde al valor final de ==>>> %.2f [%s]", dineroAConvertir, CODIGOS_MONEDAS_DISPONIBLES[1], factorDeCambio * dineroAConvertir, CODIGOS_MONEDAS_DISPONIBLES[3]));
                    }
                    break;
                case 5:
                    while (!verificarDineroValido(dineroAConvertir)) {
                        System.out.println(mensajeOpcionValida);
                        dineroAConvertir = scanner.nextInt();
                        factorDeCambio = APIFetcher(CODIGOS_MONEDAS_DISPONIBLES[3], CODIGOS_MONEDAS_DISPONIBLES[2]);
                        history.add(new HistoryClass(CODIGOS_MONEDAS_DISPONIBLES[3], CODIGOS_MONEDAS_DISPONIBLES[2], dineroAConvertir, factorDeCambio * dineroAConvertir));
                        System.out.println(String.format("El valor %.2f [%s] correponde al valor final de ==>>> %.2f [%s]", dineroAConvertir, CODIGOS_MONEDAS_DISPONIBLES[3], factorDeCambio * dineroAConvertir, CODIGOS_MONEDAS_DISPONIBLES[2]));
                    }
                    break;
                case 6:
                    while (!verificarDineroValido(dineroAConvertir)) {
                        System.out.println(mensajeOpcionValida);
                        dineroAConvertir = scanner.nextInt();
                        factorDeCambio = APIFetcher(CODIGOS_MONEDAS_DISPONIBLES[2], CODIGOS_MONEDAS_DISPONIBLES[3]);
                        history.add(new HistoryClass(CODIGOS_MONEDAS_DISPONIBLES[2], CODIGOS_MONEDAS_DISPONIBLES[3], dineroAConvertir, factorDeCambio * dineroAConvertir));
                        System.out.println(String.format("El valor %.2f [%s] correponde al valor final de ==>>> %.2f [%s]", dineroAConvertir, CODIGOS_MONEDAS_DISPONIBLES[2], factorDeCambio * dineroAConvertir, CODIGOS_MONEDAS_DISPONIBLES[3]));
                    }
                    break;
                case 7:
                    if (history.size() == 0) System.out.println("El historial está vacío");
                    else {
                        for (History history1 : history) {
                            System.out.println(history1);
                        }
                    }
                    break;
                case 8:
                    System.out.println(mensajeSalida);
                    break;
                default:
                    System.out.println(mensajeOpcionInvalida);
                    break;
            }
        }
    }
}