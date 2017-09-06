package com.example.materialdesigntextbox;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * El ancho y el alineamiento vertical y la linea de fondo del EditText son diferentes
 * en Android 4.4, 5.0 , 5.1 y 6
 *
 *I. TextInputLayout provee una etiqueta flotante y no hay manera de deshabilitarla.
 *   Ocurre: en todas las versiones hasta v23.1.1
 *II. La etiqueta TextInputLayout es coloreada en rojo cuando se llama al método setError.
 *   Ocurre: sólo en la versión v23.1.1
 *
 *Si tu SearchView no está en la activity de búsqueda: cuando ocurre una búsqueda se desencadena
 * el ciclo normal para las activities implicadas. Como resultado, la activity actual queda pausada
 * y la activity de búsqueda se crea y pasa al frente.
 *
 *Si tu SearchView está en la activity de búsqueda, entonces ocurre una de estas dos cosas:
 *   Por defecto: la activity de búsqueda recibe una llamada a su método onCreate con un
 *   intent tipo ACTION_SEARCH. Esto provocará que se creen dos instancias de la activity
 *   de búsqueda (una sobre la otra).
 *   Si has definido android:launchMode como singleTop: la activity de búsqueda recibe
 *   una llamada a su método onNewIntent con un intent tipo  ACTION_SEARCH.
 *   Esto evita que la activity sea duplicada.
 *
 *
 */


public class MainActivity extends AppCompatActivity {

    AutoCompleteTextView autoCompleteTextView;
    CustomerAdapter adapter = null;
    ArrayList<Customer> customers = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SearchView searchView = (SearchView) findViewById(R.id.search);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //searchFor(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //filterSearchFor(query);
                return true;
            }
        });

        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())){
            String query = intent.getStringExtra(SearchManager.QUERY);
            //doMySearch(query);
        }


        TextInputLayout inputLayout = (TextInputLayout)findViewById(R.id.editText2);
        inputLayout.setError("single-line text es requerido");//muestra el error
        //inputLayout.setError(null);//esconde el error

//        int layoutItemId = android.R.layout.simple_dropdown_item_1line;
//        String[] dogArr = getResources().getStringArray(R.array.dogs_list);
//        List<String> dogList = Arrays.asList(dogArr);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, layoutItemId, dogList);
//
//        AutoCompleteTextView autocompleteView = (AutoCompleteTextView) findViewById(R.id.autocomplete_view);
//        autocompleteView.setAdapter(adapter);

        customers = new ArrayList<>();
        customers = myCustomerData(customers);
        adapter = new CustomerAdapter(this, customers);

        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autocomplete_view);
        autoCompleteTextView.setAdapter(adapter);
    }

    private ArrayList<Customer> myCustomerData(ArrayList<Customer> customers) {
        customers.add(new Customer("Balendra", "Singh", 1, R.mipmap.ic_launcher));
        customers.add(new Customer("Bipin", "Arora", 2, R.mipmap.ic_launcher));
        customers.add(new Customer("Babulal", "Marandi", 3, R.mipmap.ic_launcher));


        customers.add(new Customer("Aishwarya", "Rai", 8, R.mipmap.ic_launcher));
        customers.add(new Customer("Aisin", "Kaif", 9, R.mipmap.ic_launcher));
        customers.add(new Customer("Airshi", "Khan", 10, R.mipmap.ic_launcher));

        return customers;
    }

}
