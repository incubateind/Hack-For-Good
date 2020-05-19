package com.snapsid.dotquestionmark.activity;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.snapsid.dotquestionmark.R;

public class Faq extends AppCompatActivity {

    private Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        b1=(Button) findViewById(R.id.que1);
        b2=(Button) findViewById(R.id.qu2);
        b3=(Button) findViewById(R.id.qu3);
        b4=(Button) findViewById(R.id.qu4);
        b5=(Button) findViewById(R.id.qu5);
        b6=(Button) findViewById(R.id.qu6);
        b7=(Button) findViewById(R.id.qu7);
        b8=(Button) findViewById(R.id.qu8);
        b9=(Button) findViewById(R.id.qu9);
        b10=(Button) findViewById(R.id.qu10);
        b11=(Button) findViewById(R.id.qu11);
        b12=(Button) findViewById(R.id.qu12);
        b13=(Button) findViewById(R.id.qu13);
        //b1=(Button) findViewById(R.id.qu2);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Faq.this, Fa.class);
                intent.putExtra("1", "Q: What is coronavirus disease 2019 (COVID-19)?\n" +
                        "\n\nA: Coronavirus disease 2019 (COVID-19) is a respiratory disease that can spread from one person to the other. There are many types of human coronaviruses, including some that commonly cause mild upper-respiratory tract illnesses. COVID-19 is a new disease, caused by a novel (or new) coronavirus that has not previously been seen in humans.Symptoms that are currently reported by the patients suffering from COVID-19 have reported mild to severe respiratory illness, along with fever, cough, and difficulty in breathing.");
                startActivity(intent);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Faq.this, Fa.class);
                intent.putExtra("1", "Q: What is a novel coronavirus?\n" +
                        "\n\nA: A novel coronavirus is a new coronavirus that has not been previously identified. This virus causing coronavirus disease 2019 (COVID-19), is not the same as the previously identified coronaviruses, which commonly circulated among humans and caused common cold and mild fever.");
                startActivity(intent);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Faq.this, Fa.class);
                intent.putExtra("1", "Q: Are there any vaccines or other medical products to prevent COVID-19?\n" +
                        "\n\nA: currently, no particular vaccine has been introduced to prevent coronavirus disease 2019 (COVID-19). However, the FDA, that is Food and Drug Administration, is working with vaccine developers and other researchers and manufacturers to help expedite the development and availability of medical products such as vaccines, antibodies, and drugs to prevent COVID-19.\n");
                startActivity(intent);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Faq.this, Fa.class);
                intent.putExtra("1", "Q: Are antibiotics effective in preventing or treating COVID-19?\n" +
                        "\n\nA: No. Antibiotics are not effective against viruses.This is beacuse, antibiotics work on bacterial infections only. Since COVID-19 is caused by a virus, antibiotics will not be useful.");
                startActivity(intent);
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Faq.this, Fa.class);
                intent.putExtra("1", "Q: Can SARS-CoV-2, the virus that causes COVID-19, be transmitted by blood transfusion?\n" +
                        "\n\nA: In general, respiratory viruses are not known to be transmitted by blood transfusion, and there have been no reported cases of transfusion-transmitted coronavirus.\n");
                startActivity(intent);
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Faq.this, Fa.class);
                intent.putExtra("1", "Q: Does COVID-19 present a risk to the safety of the nation’s blood supply?\n" +
                        "\n\nA: Since respiratory viruses are not transmitted through blood transfusion, and no such cases of transfusion-transmitted coronavirus have been reported so far, it does not present a risk to the safety of Nation's blood supply..");
                startActivity(intent);
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Faq.this, Fa.class);
                intent.putExtra("1", "Q: Is there a test for COVID-19?\n\n\n" +
                        "A: The FDA has also issued new policies and guidance to achieve more rapid testing capacity in the U.S. Since the virus that causes COVID-19, that is SARS-CoV-2 is new, there is no particular FDA-approved or cleared test to diagnose or detect COVID-19 currently.");
                startActivity(intent);
            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Faq.this, Fa.class);
                intent.putExtra("1", "Q: Should I purchase personal protective equipment such as facemasks or N95 respirators for me and my family?\n" +
                        "\n\nA: CDC does not recommend that people who are well to wear a facemask, beacuse everyone might not be able to wear a respirator, such as N95 respirators, due to medical conditions. Wearing a mask might make breathing through a repirator quite difficult. However, patients with confirmed or suspected COVID-19 should wear a facemask along with the health workers and other people who are close to someone who is infected with COVID-19.");
                startActivity(intent);
            }
        });
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Faq.this, Fa.class);
                intent.putExtra("1", "Q: Is food imported to the United States from China and other countries affected by coronavirus disease 2019 (COVID-19), at risk of spreading COVID-19?\n\n\n" +
                        "A: Currently, no evidence has been found to support the transmission of COVID-19 through imported goods and no such cases have been reported so far.\n");
                startActivity(intent);
            }
        });
        b10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Faq.this, Fa.class);
                intent.putExtra("1", "Q: Can I get sick with COVID-19 from touching food, the food packaging, or food contact surfaces, if the coronavirus was present on it?\n\n\n" +
                        "A: Currently, no evidence for transmission of COVID-19 through food or packaging has been found. It is likely that the virus that causes COVID-19 can survive on surfaces or objects.");
                startActivity(intent);
            }
        });
        b11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Faq.this, Fa.class);
                intent.putExtra("1", "Q. Can animals get novel coronavirus disease (COVID-19)?\n\n\n" +
                        "A. No evidence that animals might become a source of infection. However, more than one dog have been tested positive for the virus that causes COVID-19, based on information from the Hong Kong Agriculture, Fisheries & Conservation DepartmentExternal Link Disclaimer.");
                startActivity(intent);
            }
        });
        b12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Faq.this, Fa.class);
                intent.putExtra("1", "Q. If I have COVID-19, can I infect my pet? Can I get COVID-19 from my pet?\n\n\n" +
                        "A. There is currently no evidence that pets can spread COVID-19 or that they might be a source of infection in the United States. It is advised that if you are sick with COVID-19, limit the contact with your pet, including petting, snuggling, being kissed or licked, and sharing food, wash hands before and after you interact with pets.");
                startActivity(intent);
            }
        });
        b13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Faq.this, Fa.class);
                intent.putExtra("1", "Q: Why aren’t blood centers testing donors for SARS-CoV-2?\n\n\n" +
                        "A: At this time, the FDA does not recommend using laboratory tests to screen blood. Someone who has symptoms of COVID-19, including fever, cough, and shortness of breath, is not healthy enough to donate blood. Standard screening processes already in place will mean that someone with these symptoms will not be allowed to donate.\n");
                startActivity(intent);
            }
        });

    }
}
