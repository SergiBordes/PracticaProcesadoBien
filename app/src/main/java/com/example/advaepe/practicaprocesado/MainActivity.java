package com.example.advaepe.practicaprocesado;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.FontResourcesParserCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private RecyclerView.Adapter adapter;
    private static final String TAG = "MainActivity";
    static long FECHA[] = {1288122023000L, 1288122023000L, 1288122223000L, 1288122223000L,
            1288122223000L, 1288122623000L, 1288122623000L, 1288122623000L};
    static String HABITACION[] = {"cocina", "baño", "cocina", "baño",
            "comedor", "cocina", "baño", "comedor"};
    static int TEMPERATURA[] = {18, 19, 17, 19,
            17, 19, 22, 22};

    ArrayList<temperaturaLectura> valoresConstantes = new ArrayList<>();
    Set<String> habitaciones = new HashSet<String>();
    List items = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i = 0; i < TEMPERATURA.length; i++) {
            temperaturaLectura medida = new temperaturaLectura(FECHA[i], HABITACION[i], TEMPERATURA[i]);
            valoresConstantes.add(medida);
        }
        habitaciones.addAll(Arrays.asList(HABITACION));
        calcularMedia();
        Log.i(TAG, "Habitaciones:" + habitaciones);



        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        // Usar un administrador para LinearLayout
        LinearLayoutManager lManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(lManager);

// Crear un nuevo adaptador
        adapter = new adaptadorRecycler(items);
        recyclerView.setAdapter(adapter);
    }

    public void calcularMedia() {
        double resultado;
        Map<String, Object> datos = new HashMap<>();
        for (String habitacione : habitaciones) {
            double fechaPorTemp = 0;
            long tiempoTotal = 0;
            long tiempoAnterior = 0;
            for (int i = 0; i < valoresConstantes.size(); i++) {
                if (valoresConstantes.get(i).getLugar().equals(habitacione)) {
                    Log.i(TAG, valoresConstantes.get(i).toString());
                    if (tiempoAnterior != 0) {
                        fechaPorTemp += ((valoresConstantes.get(i).getTiempo() - tiempoAnterior) * valoresConstantes.get(i).getTemperatura());
                        tiempoTotal += valoresConstantes.get(i).getTiempo() - tiempoAnterior;
                    }
                    tiempoAnterior = valoresConstantes.get(i).getTiempo();
                }


            }
            resultado = fechaPorTemp / tiempoTotal;
            Log.i(TAG, Double.toString(resultado));

            datos.put("Campo 1", resultado);

            items.add(new Habitacion(habitacione,String.valueOf(resultado)));
        }
        db.collection("temperatura").document(String.valueOf(habitaciones)).set(datos);
    }
}
