package com.druzbanarodov.relativlayoutjava;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.snackbar.Snackbar;
import com.makeramen.roundedimageview.RoundedImageView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Add_Tradition_Activity extends AppCompatActivity{
    EditText about_tradition;
    Button send_traditional;
    ImageView attachment_item0, attachment_item1, attachment_item2;

    private static final int SELECT_PHOTO_FROM_GALLERY = 1;
    private static final int TAKE_PHOTO_FROM_CAMERA = 2;
    static final int REQUEST_CODE = 123;

    private List<ImageView> traditionsPhotos;
    private List<ImageView> deleteTraditionPhotoImages;
    private SparseArray<Uri> traditionImagesUris;
    private int selectedImageIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        traditionImagesUris = new SparseArray<>();
        setContentView(R.layout.activity_add_tradition);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        about_tradition = (EditText) findViewById(R.id.edit_offer_tradition_fragment_offer_text);
        send_traditional = (Button) findViewById(R.id.button_send_tradition);
        attachment_item0 = (ImageView) findViewById(R.id.tradition_picture_0);
        attachment_item1 = (ImageView) findViewById(R.id.tradition_picture_1);
        attachment_item2 = (ImageView) findViewById(R.id.tradition_picture_2);

        //setupWidgets(view);
        attachment_item0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedImageIndex = 0;
                startDialogFragment();
            }
        });

        attachment_item1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectedImageIndex = 1;
                startDialogFragment();
            }
        });
        attachment_item2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectedImageIndex = 2;
                startDialogFragment();
            }
        });
    }

    private void setupWidgets(View root) {
        getTraditionImages(root);
        getDeleteImages(root);
        setupTraditionImageListener();
        //setupDeleteImageButtonsListener();
    }

    private void getTraditionImages(View root) {
        traditionsPhotos = new ArrayList<>();

        ImageView attachment_item0 = (ImageView) root.findViewById(R.id.tradition_picture_0);
        ImageView attachment_item1 = (ImageView) root.findViewById(R.id.tradition_picture_1);
        ImageView attachment_item2 = (ImageView) root.findViewById(R.id.tradition_picture_2);

        attachment_item0.setTag(0);
        attachment_item1.setTag(1);
        attachment_item2.setTag(2);

        traditionsPhotos.add(attachment_item0);
        traditionsPhotos.add(attachment_item1);
        traditionsPhotos.add(attachment_item2);
    }

    private void getDeleteImages(View root) {
        deleteTraditionPhotoImages = new ArrayList<>();

        ImageView deleteTraditionImage0 = (ImageView) root.findViewById(R.id.image_remove_selected_image_0);
        ImageView deleteTraditionImage1 = (ImageView) root.findViewById(R.id.image_remove_selected_image_1);
        ImageView deleteTraditionImage2 = (ImageView) root.findViewById(R.id.image_remove_selected_image_2);

        deleteTraditionImage0.setTag(0);
        deleteTraditionImage1.setTag(1);
        deleteTraditionImage2.setTag(2);

        deleteTraditionPhotoImages.add(deleteTraditionImage0);
        deleteTraditionPhotoImages.add(deleteTraditionImage1);
        deleteTraditionPhotoImages.add(deleteTraditionImage2);
    }

    private void setupTraditionImageListener() {
        View.OnClickListener listener = v -> {
            startDialogFragment();
            selectedImageIndex = (Integer) v.getTag();
        };

        for (ImageView i : traditionsPhotos) {
            i.setOnClickListener(listener);
        }
    }

    private void startDialogFragment() {
        DialogAttachment dialog = new DialogAttachment();
        setDialogClickListener(dialog);
        dialog.show(getSupportFragmentManager(), null);
    }

    private void setDialogClickListener(DialogAttachment dialog) {
        dialog.setListener(action -> {
            if (action == DialogAttachment.TAKE_PHOTO_FROM_CAMERA) {
                if(ContextCompat.checkSelfPermission(Add_Tradition_Activity.this,
                        Manifest.permission.CAMERA +
                        ContextCompat.checkSelfPermission(Add_Tradition_Activity.this,
                                Manifest.permission.READ_EXTERNAL_STORAGE)) !=
                        PackageManager.PERMISSION_GRANTED){
                    if (ActivityCompat.shouldShowRequestPermissionRationale(Add_Tradition_Activity.this,
                            Manifest.permission.CAMERA) ||
                    ActivityCompat.shouldShowRequestPermissionRationale(Add_Tradition_Activity.this,
                            Manifest.permission.READ_EXTERNAL_STORAGE)){
                        AlertDialog.Builder builder = new AlertDialog.Builder(
                                Add_Tradition_Activity.this
                        );
                        builder.setTitle("Выбор доступа");
                        builder.setMessage("Камера, чтение/запись внешнего хранилища");
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(
                                        Add_Tradition_Activity.this,
                                        new String[]{
                                                Manifest.permission.CAMERA,
                                                Manifest.permission.READ_EXTERNAL_STORAGE
                                        },
                                        REQUEST_CODE
                                );
                            }
                        });
                        builder.setNegativeButton("Отмена", null);
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }else {
                        ActivityCompat.requestPermissions(
                                Add_Tradition_Activity.this,
                                new String[]{
                                        Manifest.permission.CAMERA,
                                        Manifest.permission.READ_EXTERNAL_STORAGE
                                },
                                REQUEST_CODE
                        );
                    }
                } else {
                    startCamera();
                    Toast.makeText(getApplicationContext(),"Доступ получен", Toast.LENGTH_SHORT);
                }
            } else if (action == DialogAttachment.TAKE_PHOTO_FROM_GALLERY) {
                startGallery();
                Toast.makeText(getApplicationContext(),"Доступ получен", Toast.LENGTH_SHORT);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE){
            if ((grantResults.length>0)
            && (grantResults[0]
            +grantResults[1]
            == PackageManager.PERMISSION_GRANTED)){
                startCamera();
            }else {
                Toast.makeText(getApplicationContext(),"В доступе отказано", Toast.LENGTH_SHORT);
            }
        }
    }

    private void startGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select photo"), SELECT_PHOTO_FROM_GALLERY);
    }

    private void startCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.druzbanarodov.relativlayoutjava.fileprovider",
                        photoFile);
                traditionImagesUris.put(selectedImageIndex, photoURI);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(intent, TAKE_PHOTO_FROM_CAMERA);
            }
        }
    }

    String currentPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private String dateToString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
        return dateFormat.format(date);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_PHOTO_FROM_GALLERY) {
                Bitmap bitmap = tryToLoadBitmap(data.getData());
                traditionImagesUris.put(selectedImageIndex, data.getData());
                traditionsPhotos.get(selectedImageIndex).setImageBitmap(bitmap);
            } else if (requestCode == TAKE_PHOTO_FROM_CAMERA) {
                Uri imageUri = traditionImagesUris.get(selectedImageIndex);
                System.out.println("Индекс изображения: " + selectedImageIndex);
                System.out.println("Ссылка на изображение: " + imageUri);
                System.out.println("Ссылка на изображение: " + currentPhotoPath);
                traditionsPhotos.get(selectedImageIndex).setImageURI(imageUri);
            }

            deleteTraditionPhotoImages.get(selectedImageIndex).setVisibility(View.VISIBLE);
        }
    }

    private Bitmap tryToLoadBitmap(Uri uri) {
        ContentResolver resolver = Add_Tradition_Activity.this.getContentResolver();
        try {
            return MediaStore.Images.Media.getBitmap(resolver, uri);
        } catch (IOException e) {
            return null;
        }
    }

    //@Override
    public void onSuccessfulOffer() {
        String msg = getString(R.string.offer_tradition_fragment_successful_offer_msg);
        //Snackbar.make(getView(), msg, Snackbar.LENGTH_SHORT).show();
        resetViews();
    }

    private void resetViews() {
        //EditText offerEdit = (EditText) getView().findViewById(R.id.edit_offer_tradition_fragment_offer_text);
        //offerEdit.setText("");
        resetImages();
    }

    private void resetImages() {
        for (ImageView i : traditionsPhotos) {
            i.setImageResource(R.drawable.icon_question);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        deleteAllTempImages();
    }

    private void deleteAllTempImages() {
        for (int i = 0; i < traditionImagesUris.size(); i++) {
            Uri imageUri = traditionImagesUris.get(traditionImagesUris.keyAt(i));
            File imageFile = new File(imageUri.getPath());
            imageFile.delete();
        }
    }

}
