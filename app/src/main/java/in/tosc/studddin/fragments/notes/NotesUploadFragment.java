package in.tosc.studddin.fragments.notes;


import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import in.tosc.studddin.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotesUploadFragment extends Fragment {


    Button attachButton, uploadButton;
    EditText topicNameEdTxt, branchNameEdTxt, subjectNameEdTxt;
    String topicNameString = "", branchNameString = "", subjectNameString = "";
    ImageView imageView;
    Uri imageSelectedUri;
    public NotesUploadFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_notes_upload, container, false);

        attachButton = (Button) rootView.findViewById(R.id.notes_attach);
        uploadButton = (Button) rootView.findViewById(R.id.notes_upload);
        topicNameEdTxt = (EditText) rootView.findViewById(R.id.notes_topic);
        branchNameEdTxt = (EditText) rootView.findViewById(R.id.notes_branch);
        subjectNameEdTxt = (EditText) rootView.findViewById(R.id.notes_subject);

        attachButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);

            }

        });


        uploadButton = (Button)rootView.findViewById(R.id.notes_upload);

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(topicNameEdTxt.length() < 1){
                    Toast.makeText(getActivity(), "Please enter the Topic Name", Toast.LENGTH_SHORT)
                            .show();
                } else if (subjectNameEdTxt.length() < 1){
                    Toast.makeText(getActivity(), "Please enter the Subject Name", Toast.LENGTH_SHORT)
                            .show();
                } else if(branchNameEdTxt.length() < 1){
                    Toast.makeText(getActivity(), "Please enter the Branch Name", Toast.LENGTH_SHORT)
                            .show();
                }

            }
        });

         imageView = (ImageView) rootView.findViewById(R.id.notes_selected_image);


        return rootView;

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(resultCode == getActivity().RESULT_OK){

            if(requestCode == 1){
                imageSelectedUri = data.getData();
                String[] projection = {MediaStore.Images.Media.DATA};

                Cursor cursor = getActivity().getContentResolver().query(imageSelectedUri, projection,
                        null, null, null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(projection[0]);
                String picturePath = cursor.getString(columnIndex);
                cursor.close();
                imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
            }

        }
    }




}
