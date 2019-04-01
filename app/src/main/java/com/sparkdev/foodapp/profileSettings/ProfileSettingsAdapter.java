package com.sparkdev.foodapp.profileSettings;

import com.sparkdev.foodapp.R;
import com.sparkdev.foodapp.models.User;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.util.AdapterListUpdateCallback;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class ProfileSettingsAdapter extends RecyclerView.Adapter<ProfileSettingsAdapter.ProfileViewHolder> {

    private final ArrayList<String> textInputList;    // This will hold your data
    private LayoutInflater profileInflater;       // This will be the inflater for ContactListAdapter
    private boolean isAllValidated = true;
    private User user = User.currentUser;
    private final ArrayList<String> userInput;

    // ContactListAdapter Constructor
    public ProfileSettingsAdapter(Context context, ArrayList<String> textInputList, ArrayList<String> userInput) {
        profileInflater = LayoutInflater.from(context); // Initialize the layout inflator
        this.textInputList = textInputList; // Initialize the arraylist
        this.userInput = userInput;
    }

    // Inner class to the ContactListAdapter and extends
    public class ProfileViewHolder extends RecyclerView.ViewHolder {
        // The following variables are for the text view and the adapter for each row
        public final TextView nameTextView;
        public final TextInputLayout editTextView;
        final ProfileSettingsAdapter rowAdapter;


        // Constructor where the first parameter is to inflate the layout and the second
        // parameter is to associate the ContactViewHolder with its adapter
        public ProfileViewHolder(View itemView, ProfileSettingsAdapter adapter) {
            super(itemView);
            // Initialize the view holder's text view from the XML resources (activity_contact_list.xml)
            // Be sure to cast it to the View type that you need it to be (i.e TextView)
            nameTextView = (TextView) itemView.findViewById(R.id.row_title);
            editTextView = (TextInputLayout) itemView.findViewById(R.id.text_input);

            // Set up the adapter
            this.rowAdapter = adapter;

        }
    }


    // The onCreateViewHolder() method is very similar to the onCreate() method. In this method,
    // the LAYOUT will be inflated and it will return a view holder with the specified layout
    // and the corresponding adapter
    @Override
    public ProfileViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        // Inflate the layout
        View customView = profileInflater.inflate(R.layout.profile_edit_text_row, viewGroup, false);

        // Return the new view holder
        return new ProfileViewHolder(customView, this);
    }

    // The onBindViewHolder() connects your data to your view holder
    @Override
    public void onBindViewHolder(@NonNull final ProfileViewHolder contactViewHolder, final int i) {

        String currentInput = textInputList.get(i);     // Hold the current text field
        contactViewHolder.nameTextView.setText(currentInput); // Set text field at i position to TextView
        contactViewHolder.editTextView.getEditText().setText(userInput.get(i)); // previous user input

        contactViewHolder.editTextView.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                //validate each row
                if (i == 0) {

                    //first name
                    isAllValidated = ProfileSettings.validateFirstName(contactViewHolder.editTextView.getEditText());

                } else if (i == 1) {

                    //last name
                    isAllValidated =ProfileSettings.validateLastName(contactViewHolder.editTextView.getEditText());

                } else if (i == 2) {

                    //adress
                    isAllValidated = ProfileSettings.validateAddress(contactViewHolder.editTextView.getEditText());

                } else if (i == 3) {

                    //email
                    isAllValidated = ProfileSettings.validateEmail(contactViewHolder.editTextView.getEditText());

                } else if (i == 4) {

                    //password
                    isAllValidated = ProfileSettings.validatePassword(contactViewHolder.editTextView.getEditText());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return textInputList.size();
    }

    public boolean getIsAllValidated()
    {
        return isAllValidated;
    }


}