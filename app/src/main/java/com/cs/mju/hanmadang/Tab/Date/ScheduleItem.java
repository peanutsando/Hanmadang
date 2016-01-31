package com.cs.mju.hanmadang.Tab.Date;

// 데이터를 담고 있을 아이템 정의
public class ScheduleItem {
   // Data array
   private String[] mData;
   // True if this item is selectable
   private boolean mSelectable = true;

   // Initialize with icon and data array
   public ScheduleItem(String[] obj) {
       mData = obj;
   }

   // Initialize with icon and strings
   public ScheduleItem(String obj01, String obj02, String obj03, String obj04) {
       mData = new String[4];
       mData[0] = obj01; // 제목
       mData[1] = obj02; // 장소
       mData[2] = obj03; // 내용
       mData[3] = obj04; // 시간
   }

   // True if this item is selectable
   public boolean isSelectable() {
       return mSelectable;
   }

   // Set selectable flag
   public void setSelectable(boolean selectable) {
       mSelectable = selectable;
   }

   // Get data array
   public String[] getData() {
       return mData;
   }

   // Get data
   public String getData(int index) {
       if (mData == null || index >= mData.length) {
           return null;
       }
       return mData[index];
   }

   // Set data array
   public void setData(String[] obj) {
       mData = obj;
   }

   // Compare with the input object
   public int compareTo(ScheduleItem other) {
       if (mData != null) {
           String[] otherData = other.getData();
           if (mData.length == otherData.length) {
               for (int i = 0; i < mData.length; i++) {
                   if (!mData[i].equals(otherData[i])) {
                       return -1;
                   }
               }
           } else {
               return -1;
           }
       } else {
           throw new IllegalArgumentException();
       }
       return 0;
   }

}
