package com.example.tablayoutviewpager;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);

        //Seteo el adaptador al viewPager
        viewPager.setAdapter(new TabPagerAdapter(getSupportFragmentManager()));

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        //Pare meter un icono en el Tab Item Uno y que cambie al ser seleccionado
        TabLayout.Tab tabItemUno = tabLayout.getTabAt(0);
        tabItemUno.setIcon(R.drawable.selector_item_uno);

        //Para que solo sea un icono se pone un strin vacio en el adaptador y aqui el icono
        TabLayout.Tab tabitemTres = tabLayout.getTabAt(2);
        tabitemTres.setIcon(R.drawable.selector_item_uno);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            //Se invocará este metodo cuando una pagina nueva sea seleccionada
            @Override
            public void onPageSelected(int position) {
                //Toast.makeText(MainActivity.this,"posicion de pagina seleccionada: " + position, Toast.LENGTH_SHORT).show();
            }

            //Se invocará este metodo cuando la pagina actual es escrolada
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // Code goes here
                //Toast.makeText(MainActivity.this,"pagina escrolada: " + position, Toast.LENGTH_SHORT).show();
            }

            // Called when the scroll state changes:
            // SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING
            @Override
            public void onPageScrollStateChanged(int state) {
                // Code goes here
                //Toast.makeText(MainActivity.this,"pagina onPageScrollStateChanged: ", Toast.LENGTH_SHORT).show();
            }
        });



    }
}
