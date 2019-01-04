package fr.rouibah.marouane.securite;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import fr.rouibah.marouane.securite.classes.Nav;

public class MainCouranteActivity extends Nav {


    static final int REQUEST_IMAGE_CAPTURE = 1;
    private ImageView mImageView;
    private Bundle extras;
    private Intent takePictureIntent;
    private Bitmap imageBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_courante);


        this.menu();


        ImageView imgview = (ImageView) this.findViewById(R.id.imgview);
        Button myButton = (Button) this.findViewById(R.id.button);
        myButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick (View v) {


                takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);

                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            if (resultCode == RESULT_OK) {

                Bundle extras = data.getExtras();
                imageBitmap = (Bitmap) extras.get("data");
                //mImageView.setImageBitmap(imageBitmap);

                capture();
            }

    }

    protected void capture() {

        //ImageView myImageView = (ImageView) findViewById(R.id.imgview);
        //myImageView.setImageBitmap(imageBitmap);


        TextView txt = (TextView) findViewById(R.id.txtContent);

        BarcodeDetector detector =
                new BarcodeDetector.Builder(getApplicationContext())
                        .setBarcodeFormats(Barcode.DATA_MATRIX | Barcode.QR_CODE)
                        .build();

        if (!detector.isOperational()) {
            txt.setText("Could not set up the detector!");
            return;
        }

        Frame frame = new Frame.Builder().setBitmap(imageBitmap).build();
        SparseArray<Barcode> barcodes = detector.detect(frame);


            Barcode thisCode = barcodes.valueAt(0);
        TextView txtView = (TextView) findViewById(R.id.txtContent);
        txtView.setText(thisCode.rawValue);

    }
}
