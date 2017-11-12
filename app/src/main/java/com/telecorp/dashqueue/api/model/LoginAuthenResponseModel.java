package com.telecorp.dashqueue.api.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Saran on 12/11/2560.
 */

public class LoginAuthenResponseModel implements Parcelable {
    @SerializedName("PatientQueueList")
    private ArrayList<PatientQueue> patientQueueArrayList = new ArrayList<>();
    @SerializedName("WaitingQueue")
    private WaitingQueue waitingQueue = null;
    @SerializedName("WaitingList")
    private ArrayList<Waiting> waitingList = new ArrayList<>();

    public LoginAuthenResponseModel() {
    }


    public ArrayList<PatientQueue> getPatientQueueArrayList() {
        return patientQueueArrayList;
    }

    public WaitingQueue getWaitingQueue() {
        return waitingQueue;
    }

    public ArrayList<Waiting> getWaitingList() {
        return waitingList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.patientQueueArrayList);
        dest.writeParcelable(this.waitingQueue, flags);
        dest.writeTypedList(this.waitingList);
    }

    protected LoginAuthenResponseModel(Parcel in) {
        this.patientQueueArrayList = in.createTypedArrayList(PatientQueue.CREATOR);
        this.waitingQueue = in.readParcelable(WaitingQueue.class.getClassLoader());
        this.waitingList = in.createTypedArrayList(Waiting.CREATOR);
    }

    public static final Parcelable.Creator<LoginAuthenResponseModel> CREATOR = new Parcelable.Creator<LoginAuthenResponseModel>() {
        @Override
        public LoginAuthenResponseModel createFromParcel(Parcel source) {
            return new LoginAuthenResponseModel(source);
        }

        @Override
        public LoginAuthenResponseModel[] newArray(int size) {
            return new LoginAuthenResponseModel[size];
        }
    };
}
