/*
 * Copyright 2009-2010 Eric Bruno, Greg Bollella. All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  
 *
 * Neither the name of the Book, 
 * "Real-Time Java Programming with Java RTS"
 * nor the names of its authors may be used to endorse or promote 
 * products derived from this software without specific prior written 
 * permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * See the GNU General Public License version 2 for more details. 
 * You should have received a copy of the GNU General Public License 
 * version 2 along with this work; if not, write to the: 
 * Free Software Foundation, Inc. 
 * 51 Franklin St, Fifth Floor 
 * Boston, MA 02110-1301 USA.
 */
package com.sun.oss.trader.data;

public class MarketPrice 
{
    // Private data
    //
    private double price;
    private long quantity;
    private StringBuffer timestamp;
    private StringBuffer symbol;

    // Constructors
    //
    private MarketPrice() { }
    public MarketPrice(StringBuffer s, double p, long q, StringBuffer t)
    {
        price = p;
        quantity = q;
        timestamp = new StringBuffer(t);
        symbol = new StringBuffer(s);
    }

    // Public methods
    //
    public double getPrice() {
        return price;
    }
    public void setPrice( double price ) {
        this.price = price;
    }

    public long getQuantity() {
        return quantity;
    }
    public void setQuantity( long quantity ) {
        this.quantity = quantity;
    }

    public StringBuffer getTimestamp() {
        return timestamp;
    }

    public StringBuffer getSymbol() {
        return symbol;
    }
}
