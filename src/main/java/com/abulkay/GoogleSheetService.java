package com.abulkay;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.AppendValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by AbdulBasit KABIR on 3/16/18.
 */
public class GoogleSheetService {
    public static void addMultiValue(List<Object>... rows) {
        try{
            Sheets service = Util.getSheetsService();
            List<List<Object>> values = Arrays.asList(rows);
            ValueRange body = new ValueRange()
                    .setValues(values);
            String range = "Sheet1";;
            AppendValuesResponse result =
                    service.spreadsheets().values().append(Util.spreadsheetId, range, body)
                            .setValueInputOption("RAW")
                            .execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}