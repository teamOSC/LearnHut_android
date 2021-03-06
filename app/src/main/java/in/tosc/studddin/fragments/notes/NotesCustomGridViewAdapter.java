package in.tosc.studddin.fragments.notes;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.parse.ParseFile;

import java.util.ArrayList;

import in.tosc.studddin.R;

/**
 * Created by raghav on 25/01/15.
 */
public class NotesCustomGridViewAdapter extends RecyclerView.Adapter<NotesCustomGridViewAdapter.ViewHolder> {

    private Context mContext;
    private int gridLayout;

    private ArrayList<String> notesCollegeName, notesBranchName, notesTopicName, notesSubjectName, uploadedBy;
    private ArrayList<ArrayList<ParseFile>> notesFirstImage;

    public NotesCustomGridViewAdapter(Context c, ArrayList<String> notesCollegeName, ArrayList<String> notesBranchName,
                                      ArrayList<String> notesTopicName, ArrayList<String> notesSubjectName,
                                      ArrayList<ArrayList<ParseFile>> notesFirstImage, ArrayList<String> uploadedBy) {

        mContext = c;
        this.notesBranchName = notesBranchName;
        this.notesCollegeName = notesCollegeName;
        this.notesSubjectName = notesSubjectName;
        this.notesTopicName = notesTopicName;
        this.notesFirstImage = notesFirstImage;
        this.uploadedBy = uploadedBy;
        gridLayout = R.layout.notes_search_gridview_item;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View grid = LayoutInflater.from(parent.getContext()).inflate(gridLayout, parent, false);

        return new ViewHolder(grid);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.topicNameTxtView.setText(notesTopicName.get(position));

        Uri notesThumbUri = Uri.parse(notesFirstImage.get(position).get(0).getUrl());

        holder.notesImage.setImageURI(notesThumbUri);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotesCustomDialog notesCustomDialog = new NotesCustomDialog(mContext,
                        notesCollegeName, notesBranchName, notesTopicName, notesSubjectName, position, uploadedBy);
                notesCustomDialog.setTitle(mContext.getString(R.string.listings_details));
                notesCustomDialog.show();
            }
        });


    }


    @Override
    public int getItemCount() {
        return notesTopicName.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView topicNameTxtView;
        public SimpleDraweeView notesImage;


        public ViewHolder(View itemView) {
            super(itemView);

            topicNameTxtView = (TextView) itemView.findViewById(R.id.notes_gridview_topicname);
            notesImage = (SimpleDraweeView) itemView.findViewById(R.id.notes_gridview_image_view);

        }

    }
}
