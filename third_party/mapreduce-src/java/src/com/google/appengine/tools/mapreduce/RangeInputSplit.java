/*
 * Copyright 2010 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.appengine.tools.mapreduce;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.InputSplit;

/**
 * The input split class for {@link RangeInputFormat}.
 *
 * The inputs generated by this split are inclusive of start and exclusive of
 * end (i.e. [start, end) ).
 *
 */
public class RangeInputSplit extends InputSplit implements Writable {
  private long splitStart;
  private long splitEnd;

  public RangeInputSplit() {
  }

  public RangeInputSplit(long start, long end) {
    splitStart = start;
    splitEnd = end;
  }

  @Override
  public long getLength() throws IOException, InterruptedException {
    return splitEnd - splitStart;
  }

  @Override
  public String[] getLocations() throws IOException, InterruptedException {
    return null;
  }

  @Override
  public void readFields(DataInput input) throws IOException {
    splitStart = input.readLong();
    splitEnd = input.readLong();
  }

  @Override
  public void write(DataOutput output) throws IOException {
    output.writeLong(splitStart);
    output.writeLong(splitEnd);
  }

  public long getSplitStart() {
    return splitStart;
  }

  public long getSplitEnd() {
    return splitEnd;
  }
}
