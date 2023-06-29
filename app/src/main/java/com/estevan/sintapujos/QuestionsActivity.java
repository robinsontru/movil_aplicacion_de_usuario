package com.estevan.sintapujos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.estevan.sintapujos.Adapters.CategoryAdapter;
import com.estevan.sintapujos.Models.QuestionModel;
import com.estevan.sintapujos.databinding.ActivityQuestionsBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class QuestionsActivity extends AppCompatActivity {

    ActivityQuestionsBinding binding;

    private ArrayList<QuestionModel> list;
    private int count = 0;
    private int position = 0;
    public static int score = 0;
    public static int error = 0;
    public static int sizelist;
    CountDownTimer timer;

    FirebaseDatabase database;
    String categoryName;
    private int set;
    private boolean timerStopped = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuestionsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        database = FirebaseDatabase.getInstance();

        categoryName = CategoryAdapter.category;
        set = CategoryAdapter.setnum;

        list = new ArrayList<>();

        resetTimer();
        timer.start();

        database.getReference().child("Sets").child(categoryName).child("questions")
                .orderByChild("setNum").equalTo(set)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (snapshot.exists()) {

                            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                                QuestionModel model = dataSnapshot.getValue(QuestionModel.class);
                                list.add(model);

                            }

                            if (list.size() > 0) {

                                for (int i = 0; i < 4; i++) {

                                    binding.optinContainers.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            checkAnsw((Button) view);

                                        }
                                    });

                                }

                                playAnimation(binding.question, 0, list.get(position).getQuestion());

                                binding.btnNext.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                        binding.btnNext.setEnabled(false);
                                        binding.btnNext.setAlpha(0.3f);

                                        enableOption(true);
                                        position++;

                                        if (position == list.size()) {

                                            stopTimer();

                                            Intent intent = new Intent(QuestionsActivity.this, ScoreActivity.class);
                                            // intent.putExtra("correctAnsw",score);
                                            //  intent.putExtra("totalQuestion",list.size());
                                            sizelist = list.size();
                                            startActivity(intent);
                                            finish();

                                            return;

                                        }

                                        count = 0;

                                        playAnimation(binding.question, 0, list.get(position).getQuestion());

                                    }
                                });

                            } else {

                                Toast.makeText(QuestionsActivity.this, "la pregunta no existe", Toast.LENGTH_SHORT).show();

                            }

                        } else {

                            Toast.makeText(QuestionsActivity.this, "la pregunta no existe", Toast.LENGTH_SHORT).show();

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                        Toast.makeText(QuestionsActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

    }

    private void resetTimer() {

        timer = new CountDownTimer(35000, 1000) {
            @Override
            public void onTick(long l) {

                binding.timer.setText(String.valueOf(l / 1000));

            }

            @Override
            public void onFinish() {

                if (!timerStopped){

                    Intent intent = new Intent(QuestionsActivity.this, ScoreActivity.class);
                    startActivity(intent);

                    Toast.makeText(QuestionsActivity.this, "Tiempo Terminado", Toast.LENGTH_SHORT).show();
                    finish();

                }

            }
        };

    }

    private void stopTimer(){

        timerStopped = true;
        timer.cancel();

    }

    private void enableOption(boolean enable) {

        for (int i = 0; i < 4; i++) {

            binding.optinContainers.getChildAt(i).setEnabled(enable);

            if (enable) {

                binding.optinContainers.getChildAt(i).setBackgroundResource(R.drawable.btn_optin_back);

            }

        }

    }

    private void playAnimation(View view, int value, String data) {

        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500).setStartDelay(100)
                .setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(@NonNull Animator animator) {

                        if (value == 0 && count < 4) {

                            String option = "";

                            if (count == 0) {

                                option = list.get(position).getOptionA();

                            } else if (count == 1) {

                                option = list.get(position).getOptionB();

                            } else if (count == 2) {

                                option = list.get(position).getOptionC();

                            } else if (count == 3) {

                                option = list.get(position).getOptionD();

                            }

                            playAnimation(binding.optinContainers.getChildAt(count), 0, option);
                            count++;

                        }

                    }

                    @Override
                    public void onAnimationEnd(@NonNull Animator animator) {

                        if (value == 0) {

                            try {

                                ((TextView) view).setText(data);
                                binding.numIndicator.setText(position + 1 + "/" + list.size());

                            } catch (Exception e) {

                                ((Button) view).setText(data);

                            }

                            view.setTag(data);
                            playAnimation(view, 1, data);

                        }

                    }

                    @Override
                    public void onAnimationCancel(@NonNull Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(@NonNull Animator animator) {

                    }
                });
    }

private void checkAnsw(Button selectedOption) {

                enableOption(false);

                binding.btnNext.setEnabled(true);
                binding.btnNext.setAlpha(1);

                if (selectedOption.getText().toString().equals(list.get(position).getCorrectAnsw())) {

                    score = score + 5;

                    selectedOption.setBackgroundResource(R.drawable.right_answ);

                } else {

                    selectedOption.setBackgroundResource(R.drawable.wrong_answ);

                    error = error + 1;

                    Button correctOption = (Button) binding.optinContainers.findViewWithTag(list.get(position).getCorrectAnsw());
                    correctOption.setBackgroundResource(R.drawable.right_answ);
                }
            }

    }
