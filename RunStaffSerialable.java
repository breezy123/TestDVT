try 
        {
            input = new ObjectInputStream(new FileInputStream("Movie.ser"));
            String dvdTable = "CREATE TABLE DVD (DVD_NUMBER INTEGER NOT NULL, DVD_TITLE VARCHAR(150) NOT NULL, CATEGORY VARCHAR(100) NOT NULL, NEW_RELEASE YESNO, AVAILABLE_FOR_RENT YESNO, PRIMARY KEY(DVD_NUMBER))";
            stmt.executeUpdate(dvdTable);
            while(true)
            {
                try
                {
                    objectDVD = (DVD)input.readObject();
                    dvdList.add(objectDVD);
                    String insertDVD = "INSERT INTO DVD VALUES("+dvdList.get(count).getDvdNumber()+","
                         + "'"+dvdList.get(count).getTitle() +"','"+dvdList.get(count).getCategory()+"',"
                         + ""+dvdList.get(count).isNewRelease()+","+dvdList.get(count).isAvailable()+")";
                    stmt.executeUpdate(insertDVD);
                } 
                catch (EOFException eof)
                {
                JOptionPane.showMessageDialog(null, "Movie Data Stored...");
                return;
                }
                catch (IOException ioe) 
                {
                JOptionPane.showMessageDialog(null, ioe.getMessage() + " IOException Error Writting to Customer Table ");
                return;
                }
                catch (ClassCastException cast)
                {
                JOptionPane.showMessageDialog(null, cast.getMessage() + " Class Cast Exception Error Writting to Customer Table");
                return;
                }
                catch (ClassNotFoundException not) 
                {
                JOptionPane.showMessageDialog(null, not.getMessage() + " Class Not Found Error Writting to Customer Table");
                return;
                }
                catch (SQLException sql) 
                {
                JOptionPane.showMessageDialog(null, sql.getMessage() + " SQL Error Writting to Customer Table");
                return;
                }
                count++;
            }
            
        } 
        catch (SQLException sql)
        {
            JOptionPane.showMessageDialog(null, sql.getMessage() + " SQL Error Creating Customer Table");
        }
        catch(IOException ioe)
        {
            JOptionPane.showMessageDialog(null, ioe.getMessage() + " IOException Error Creating Customer Table");
        }
        sendMessage(count +" Movie Records Captured");
    }